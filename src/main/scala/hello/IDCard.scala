package hello

import java.util.Date
import javax.validation.constraints.NotNull

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.data.annotation.Id

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
class IDCard{
  @Id
  var card_id: String= _
  @NotNull
  var card_name: String= _
  @NotNull
  var card_number: String= _
  @JsonFormat(pattern = "MM-dd-yyyy")
  var expiration_date: Date = _

  def this(card_id:String,card_name:String,card_number:String,
           expiration_date: Date) {
    this()
    this.card_id=card_id
    this.card_name=card_name
    this.card_number=card_number
    this.expiration_date=expiration_date
  }
  def getCard_id() : String= card_id
  def serCard_id(card_id:String) {
    this.card_id=card_id
  }
  def getCard_name() : String= card_name
  def setCard_name(card_name:String) {
    this.card_name=card_name
  }
  def getCard_number() : String= card_number
  def setCard_number(card_number:String) {
    this.card_number=card_number
  }
  def getExpiration_date() : Date = expiration_date

  def setExpiration_date(expiration_date: Date) {
    this.expiration_date=expiration_date
  }
}