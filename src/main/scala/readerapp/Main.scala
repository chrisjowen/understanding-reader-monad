package readerapp

import cats.data._

object Main extends App {

  class FakeController(env: Env) {
    def show(userId: Int) = {
//      Tied to default service here, even though sub dependencies magicalluy resolved????
      val user = DefaultUserService.getUser(userId).run(env)
      print(user)
    }

    private def run[A](reader: Reader[Env, A]): A = {
      reader(env)
    }
  }

 new FakeController(DefaultEnv).show(123)
}
