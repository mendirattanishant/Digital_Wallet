package hello

import org.springframework.data.mongodb.core.MongoTemplate
import com.mongodb.MongoClient
import org.springframework.context.annotation.{Bean, Configuration}

//remove if not needed

@Configuration
class SpringMongoConfig {

  @Bean
  def mongoTemplate(): MongoTemplate = {

    val mongo_client = new MongoClient("ds049180.mongolab.com:49180")//To establish connection

    val db=mongo_client.getDB("273_assignment_2")//To access db
//
    //
    //
    //
    val auth=db.authenticate("nishant","nishant@1".toCharArray)//Authenticate


    //
    //
    val mongo_Template=new MongoTemplate(mongo_client,"273_assignment_2")//To

    mongo_Template

  }
}