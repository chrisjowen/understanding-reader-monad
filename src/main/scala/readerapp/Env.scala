package readerapp

import cats.data.Reader
import readerapp.model.User
import readerapp.repositories.{Repositories, Services, UserRepo}
import readerapp.services.UserService

trait Env {
  def repositories : Repositories
  def services : Services
}

object Env {
  val env  = Reader[Env, Env](identity)
  val repositories = env.map(_.repositories)
  val services = env.map(_.services)
}


//Implemention injected ad edges
object DefaultUserRepo extends UserRepo {
   def get(userId: Int): User = User("Chris")
   def find(email: String): User = User("Bob")
}
object DefaultRepositories extends  Repositories {
   def userRepo: UserRepo = DefaultUserRepo
}

object DefaultServices extends Services {
   def userService: UserService = DefaultUserService
}

object DefaultEnv extends Env {
   def repositories: Repositories = DefaultRepositories
   def services: Services = DefaultServices
}
object DefaultUserService extends UserService {

  //  Define dependencies needed as imports
  import readerapp.repositories.Repositories.userRepo

  def getUser(userId: Int) = userRepo map {repo =>
//    Do some ochestration here
    val realID = userId + 10
    repo.get(realID)
  }
  def getUserByEmail(email: String) = userRepo map (_.find(email))
}

