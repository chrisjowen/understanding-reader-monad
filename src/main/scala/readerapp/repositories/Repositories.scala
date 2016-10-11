package readerapp.repositories

import cats.data.Reader

trait Repositories {
  def userRepo : UserRepo
}

object Repositories {
  import readerapp.Env.repositories

  val userRepo = repositories map(_.userRepo)
}