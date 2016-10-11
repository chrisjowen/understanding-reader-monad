package readerapp.repositories

import readerapp.services.UserService

trait Services {
  def userService : UserService
}

object Services {
  import readerapp.Env.services

  val userRepo = services map(_.userService)
}