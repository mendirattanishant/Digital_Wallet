package hello

import java.util.concurrent.atomic.AtomicLong
import java.util.{Date, HashMap}
import javax.validation.Valid
import javax.ws.rs.core.{CacheControl, EntityTag}

import com.justinsb.etcd.EtcdClient
import com.justinsb.etcd.EtcdResult
import org.bson.types.ObjectId
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.{Criteria, Query}
import org.springframework.http.{HttpHeaders, HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._
import java.net.URI


object UserController
{
  var hm1: HashMap[String, User] = new HashMap[String, User]()
  var app_context: ApplicationContext = new AnnotationConfigApplicationContext(classOf[SpringMongoConfig])
  var mongo_Operation: MongoOperations = app_context.getBean("mongoTemplate").asInstanceOf[MongoOperations]

}
@RestController
  class UserController {

  private val counter = new AtomicLong()
  private val dt = new Date()
  var client:EtcdClient=new EtcdClient(URI.create("http://54.183.114.126:4001/"))

  @RequestMapping(method = Array(RequestMethod.POST), value = Array("/api/v1/users"), consumes = Array("application/json"))
  @ResponseStatus(HttpStatus.CREATED)
  def User(@Valid @RequestBody(required = true) user: User): User = {
    val x = new ObjectId()
    user.user_id="u-"+ x
    user.date_created = new Date()
    user.date_updated=new Date()
    user.date_updated=new Date()
   // UserController.hm1.put(user.getUser_id, user)
    UserController.mongo_Operation.save(user)
    user
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = Array("/api/v1/users/{user_id}"), method = Array(RequestMethod.GET))
  @ResponseBody
  def usersInfo(
                 @PathVariable("user_id") _id: String,
                 @RequestHeader(value="Etag", required=true , defaultValue="") entag: String
                 ) : Any = {
   // var user= UserController.hm1.get(user_id)
    val user = UserController.mongo_Operation.findOne(new Query(Criteria.where("_id").is(_id)), classOf[User])
    val tag: String = entag
    val cache_c: CacheControl = new CacheControl()
    cache_c.setMaxAge(216)
    val etag: EntityTag = new EntityTag(Integer.toString(user.date_updated.hashCode()))
    val responseHeader: HttpHeaders = new HttpHeaders()
    responseHeader.setCacheControl(cache_c.toString())
    responseHeader.set("Etag", etag.getValue())
    if(etag.getValue().equalsIgnoreCase(tag)){
      new ResponseEntity[String]( null, responseHeader, HttpStatus.NOT_MODIFIED )
    } else {
      new ResponseEntity[User]( user, responseHeader, HttpStatus.OK )
    }
  }

  @RequestMapping(method = Array(RequestMethod.PUT), value = Array("/api/v1/users/{user_id}"), consumes = Array("application/json"))
  @ResponseStatus(HttpStatus.CREATED)
  def User1(@PathVariable("user_id") user_id: String, @RequestBody User1: User): User = {
    //val u = UserController.hm1.get(user_id).asInstanceOf[User]
    val user = UserController.mongo_Operation.findOne(new Query(Criteria.where("_id").is(user_id)), classOf[User])
    user.setEmail(User1.getEmail)
    user.setPassword(User1.getPassword)
    user.setName(User1.getName)
    user.setDate_updated(new Date())
    UserController.mongo_Operation.save(user)
    //UserController.mongoOperation.findOne(new Query(Criteria.where("user_id").is(user_id),classOf[User]))
    //UserController.mongoOperation.findOne(new Query(Criteria.where("user_id").is(user_id)), classOf[User])
    user
  }

  @RequestMapping(method=Array(RequestMethod.GET), value= Array("/api/v1/counter"))
  def etcdobject : String = {
    var key="/009994387/counter"
    var result:EtcdResult= null
    result=this.client.get(key)
    var initial_count = result.node.value.toInt
    var final_count = initial_count + 1
    var update = this.client.set(key, final_count.toString)
    result = this.client.get(key)
    return result.node.value

  }
  @RequestMapping(method=Array(RequestMethod.GET), value= Array("/api/v1/health"))
  def etcobject : String = {
    return ""

  }


}

