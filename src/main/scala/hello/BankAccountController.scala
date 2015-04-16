package hello

import java.util.ArrayList
import javax.validation.Valid

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.query.{Criteria, Query}
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.http.{HttpStatus, MediaType}
import org.springframework.web.bind.annotation.{PathVariable, RequestBody, RequestMapping, RequestMethod, ResponseStatus, RestController}
import org.springframework.web.client.RestTemplate

@RestController
class BankAccountController{

  //private val counter=new AtomicLong()

  @RequestMapping(method = Array(RequestMethod.POST),value = Array("/api/v1/users/{user_id}/bankaccounts"),consumes=Array("application/JSON"))
  @ResponseStatus(HttpStatus.CREATED)
  def b_id(@PathVariable("user_id") _id :String,@Valid @RequestBody(required = true) ba_id : BankAccount) : Any = {
    //val bid=UserController.hm1.get(user_id).asInstanceOf[User]
    val user = UserController.mongo_Operation.findOne(new Query(Criteria.where("_id").is(_id)), classOf[User])

    val routing_number=ba_id.getRouting_number()

    val rest=new RestTemplate() //To consume REST
//

    //
    val message_Converter_List = rest.getMessageConverters()
//
    val json_Message_Converter = new MappingJackson2HttpMessageConverter()//HTTP message convertor
//
    val Media_Types =new  ArrayList[MediaType]()
////////////////**********************************************************///
    Media_Types.add(new MediaType("text", "plain", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET))

    Media_Types.add(new MediaType("application", "json", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET))
    //supportedMediaTypes.add(jsonMessageConverter.getSupportedMediaTypes)


    json_Message_Converter.setSupportedMediaTypes(Media_Types)

    message_Converter_List.add(json_Message_Converter)

    rest.setMessageConverters(message_Converter_List)
    //To set message convertors in text/plain arraylist
    val bank_api=rest.getForObject("http://www.routingnumbers.info/api/data.json?rn="+ routing_number ,classOf[BankAccountRestConsume],routing_number)

    if(bank_api.getCode()!=200)
    {
      bank_api
    }
    else
    {
      ba_id.setAccount_name(bank_api.getCustomer_name())
      // System.out.println(ba_id.getAccount_name())
      val x=new ObjectId()// creates new objectID
      ba_id.ba_id="b-"+x
      user.arrlist_BankAccount.add(ba_id)
      UserController.mongo_Operation.save(user)
      ba_id
    }

  }
  @RequestMapping(method=Array(RequestMethod.GET), value= Array("/api/v1/users/{user_id}/bankaccounts"),consumes=Array("application/JSON"))
  def b_id(@PathVariable("user_id") _id:String) : java.util.ArrayList[BankAccount] = {
   // val bid=UserController.hm1.get(user_id)
   val user = UserController.mongo_Operation.findOne(new Query(Criteria.where("_id").is(_id)), classOf[User])
    user.arrlist_BankAccount
  }

  @RequestMapping(method=Array(RequestMethod.DELETE), value=Array("/api/v1/users/{user_id}/bankaccounts/{ba_id}"),consumes = Array("application/JSON"))
  @ResponseStatus(HttpStatus.NO_CONTENT)
  def b_id(@PathVariable("user_id") _id : String,@PathVariable("ba_id") ba_id : String){
    val user = UserController.mongo_Operation.findOne(new Query(Criteria.where("_id").is(_id)), classOf[User])

//    val baid=UserController.hm1.get(user_id).asInstanceOf[User]
    val itr1=user.arrlist_BankAccount.iterator()
    while (itr1.hasNext)
    {
      val element= itr1.next()
      if(element.getBa_id().equals(ba_id))
      {
        itr1.remove()
      }
    }
    UserController.mongo_Operation.save(user)
  }

}