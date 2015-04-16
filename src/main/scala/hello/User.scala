package hello

import java.util
import java.util.{ArrayList, Date}

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.data.annotation.Id

//remove if not neede
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
class User {
  @Id
  var user_id: String = _

  var arrlist= new ArrayList[IDCard]()

  var arrlist_WebLogin  = new ArrayList[WebLogin]()

  var arrlist_BankAccount = new util.ArrayList[BankAccount]()

  @NotEmpty
  var email: String = _
  @NotEmpty
  var password: String = _

  var name: String = _

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm.sss'Z'")
  var date_created: Date = _

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm.sss'Z'")
  var date_updated: Date = _

  def this(user_id: String,
           email: String,
           password: String,
           name: String,
           date_created: Date,
           date_updated: Date) {
    this()
    this.user_id = user_id
    this.email = email
    this.password = password
    this.name = name
    this.date_created = date_created
    this.date_updated = date_updated
  }

  def getUser_id(): String = user_id

  def setUser_id(user_id: String) {
    this.user_id = user_id
  }

  def getEmail(): String = email

  def setEmail(email: String) {
    this.email = email
  }

  def getPassword(): String = password

  def setPassword(password: String) {
    this.password = password
  }

  def getName(): String = name

  def setName(name: String) {
    this.name = name
  }

  def getDate_created(): Date = date_created

  def setDate_created(date_created: Date) {
    this.date_created = date_created
  }

  def getDate_updated(): Date = date_updated

  def setDate_updated(date_updated: Date) {
    this.date_updated = date_updated
  }
}
