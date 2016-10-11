package readerapp.services

import cats.data.Reader
import readerapp.Env
import readerapp.model.User


trait UserService {
  def getUser(userId: Int) : Reader[Env, User]
}



