package hello

import javax.validation.Valid

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.query.{Criteria, Query}
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.{PathVariable, RequestBody, RequestMapping, RequestMethod, ResponseStatus, RestController}

@RestController
class IDCardController{

  //var hm1: HashMap[String, IDCard] = new HashMap[String, IDCard]()

  @RequestMapping(method=Array(RequestMethod.POST), value=Array("/api/v1/users/{user_id}/idcards"),consumes = Array("application/json"))
  @ResponseStatus(HttpStatus.CREATED)
  def idcard(@PathVariable("user_id") _id : String,@Valid @RequestBody(required = true) idcard: IDCard): IDCard = {
    val user = UserController.mongo_Operation.findOne(new Query(Criteria.where("_id").is(_id)), classOf[User])
    //val id=UserController.hm1.get(user_id).asInstanceOf[User]
    //val timestamp= new Date().getTime().toString()
    //idcard.card_id= "i-"+timestamp
    val x=new ObjectId()
    idcard.card_id="c-"+x
    //id.arrlist.add(idcard)
    user.arrlist.add(idcard)
    UserController.mongo_Operation.save(user)
    //val user1= mongoOperation.findOne(new Query(Criteria.where("user_id").is(_id)), classOf[IDCard])
    //hm1.put(user_id,idcard)
    idcard
  }
  @RequestMapping(method = Array(RequestMethod.GET), value=Array("/api/v1/users/{user_id}/idcards"),consumes = Array("application/json"))
  def idcard(@PathVariable("user_id") _id: String):Any = {
   // val id=UserController.hm1.get(user_id).asInstanceOf[User]
  // val user = UserController.mongoOperation.findOne(new Query(Criteria.where("_id").is(_id)), classOf[User])
  val user= UserController.mongo_Operation.findOne(new Query(Criteria.where("_id").is(_id)), classOf[User])
  user.arrlist
    //id.arrlist
  }

  @RequestMapping(method = Array(RequestMethod.DELETE), value=Array("/api/v1/users/{user_id}/idcards/{card_id}"),consumes = Array("application/json"))
  @ResponseStatus(HttpStatus.NO_CONTENT)
  def idcard(@PathVariable("user_id") _id : String,@PathVariable("card_id") card_id :String)  {
   // val id=UserController.hm1.get(user_id).asInstanceOf[User]
   val user= UserController.mongo_Operation.findOne(new Query(Criteria.where("_id").is(_id)), classOf[User])
    val itr1=user.arrlist.iterator()
    while(itr1.hasNext)
    {
      val element=itr1.next()
      if(element.getCard_id().equals(card_id))
      {
        itr1.remove()
      }
    }
    UserController.mongo_Operation.save(user)
  }
}
