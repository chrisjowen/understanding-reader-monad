package readerapp.repositories

import readerapp.model.User


trait UserRepo {
  def get(userId: Int) : User
  def find(email: String) : User
}


