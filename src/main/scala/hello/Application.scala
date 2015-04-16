package hello
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.client.RestTemplate


@ComponentScan
@EnableAutoConfiguration
class Application

object Application {

  def main(args: Array[String]) {
    SpringApplication.run(classOf[Application])
     }
}
