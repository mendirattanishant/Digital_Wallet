package hello


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonSerialize

//To ignore rest properties of API
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
class BankAccountRestConsume{
//var routing_number : String= _
  var customer_name:String = _
  var code : Int = _
//  var new_routing_number : String = _
  var rn:String = _
  /*def getRouting_number():String = routing_number

  def setRouting_number(routing_number:String ) ={
    this.routing_number=routing_number
  }*/



  def getCode():Int = code

  def setCode(code:Int ) ={
    this.code=code
  }



  def getCustomer_name():String = customer_name

  def setCustomer_name(customer_name:String ) ={
    this.customer_name=customer_name
  }

 /* def getNew_routing_number():String = new_routing_number

  def setNew_routing_number(new_routing_number:String ) ={
    this.new_routing_number=new_routing_number
  }*/

  def getRn():String = rn

  def setRn(rn:String ) ={
    this.rn=rn
  }

}