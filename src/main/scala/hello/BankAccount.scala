package hello

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.data.annotation.Id

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
class BankAccount{
  @Id
  var ba_id : String = _
  var account_name : String= _
  @NotEmpty
  var routing_number : String = _
  @NotEmpty
  var account_number : String = _

  def  this(ba_id : String,account_name : String, routing_number : String, account_number : String){
    this()
    this.ba_id= ba_id
    this.account_name=account_name
    this.routing_number=routing_number
    this.account_number=account_number
  }
  def getBa_id():String = ba_id

  def setBa_id(ba_id:String ) ={
    this.ba_id=ba_id
  }
  def getAccount_name() : String = account_name

  def setAccount_name(account_name : String ) = {
    this.account_name=account_name

  }
  def getRouting_number() : String = routing_number

  def setRouting_number(routing_number : String) ={
    this.routing_number=routing_number
  }

  def getAccount_number() : String= account_number

  def setAccount_number(account_number : String) ={
    this.account_number=account_number
  }


}