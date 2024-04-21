package spring_orm.spring_orm

class UserAlreadyExistsException(username:String): RuntimeException()

class UserNotFoundException: RuntimeException()