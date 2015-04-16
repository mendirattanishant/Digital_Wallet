package hello

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.data.annotation.Id

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
class WebLogin
{
  @Id
  var login_id : String = _
  @NotEmpty
  var url : String = _
  @NotEmpty
  var login : String=  _
  @NotEmpty
  var password : String = _
  def this(login_id : String,
           url: String,login : String, password: String){
    this()
    this.login_id=login_id
    this.url=url
    this.login=login
    this.password=password
  }
  def getLogin_id(): String = login_id

  def setLogin_id(login_id:String){
    this.login_id = login_id
  }


  def getUrl() : String = url
  def setUrl(url : String){
    this.url= url
  }

  def getLogin() : String = login

  def setLogin(login : String){
    this.login= login
  }

  def getPassword() : String = password

  def SetPassword( password : String){

    this.password=password
  }

}