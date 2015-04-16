package hello

import javax.validation.Valid

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.query.{Criteria, Query}
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.{PathVariable, RequestBody, RequestMapping, RequestMethod, ResponseStatus, RestController}
//import org.springframework.web.bind.annotation._

@RestController
class WebLoginController{

 // var context: ApplicationContext = new AnnotationConfigApplicationContext(classOf[SpringMongoConfig])
  //var mongoOperation: MongoOperations = context.getBean("mongoTemplate").asInstanceOf[MongoOperations]
 // private val counter1 = new AtomicLong()


  @RequestMapping(method= Array(RequestMethod.POST),value= Array("/api/v1/users/{user_id}/weblogins"),consumes= Array("application/JSON"))
  @ResponseStatus(HttpStatus.CREATED)
  def weblogin( @PathVariable("user_id") _id : String,@Valid @RequestBody(required = true) weblogin : WebLogin) : WebLogin = {
    //val id=UserController.hm1.get(user_id).asInstanceOf[User]
    val user = UserController.mongo_Operation.findOne(new Query(Criteria.where("_id").is(_id)), classOf[User])
    val x=new ObjectId()
    weblogin.login_id="l-"+x
    user.arrlist_WebLogin.add(weblogin)
    UserController.mongo_Operation.save(user)
    weblogin
  }

  @RequestMapping(method=Array(RequestMethod.GET), value=Array("/api/v1/users/{user_id}/weblogins"),consumes=Array("application/JSON"))
  def weblogin(@PathVariable("user_id") _id : String): java.util.ArrayList[WebLogin] = {
    //val wid=UserController.hm1.get(user_id).asInstanceOf[User]
    val user = UserController.mongo_Operation.findOne(new Query(Criteria.where("_id").is(_id)), classOf[User])
    user.arrlist_WebLogin
  }

  @RequestMapping(method=Array(RequestMethod.DELETE), value=Array("/api/v1/users/{user_id}/weblogins/{login_id}"),consumes=Array("application/JSON"))
  @ResponseStatus(HttpStatus.NO_CONTENT)
  def weblogin(@PathVariable("user_id") _id : String,@PathVariable("login_id") login_id: String){
    val user= UserController.mongo_Operation.findOne(new Query(Criteria.where("_id").is(_id)), classOf[User])
    //val wid=UserController.hm1.get(user_id).asInstanceOf[User]
    val itr1=user.arrlist_WebLogin.iterator()
    while(itr1.hasNext)
    {
      val wwid=itr1.next()
      if(wwid.getLogin_id().equals(login_id))
      {
        itr1.remove()
      }
    }
    UserController.mongo_Operation.save(user)
  }
}