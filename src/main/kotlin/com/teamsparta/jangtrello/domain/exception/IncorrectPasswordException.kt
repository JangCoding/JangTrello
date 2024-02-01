package com.teamsparta.jangtrello.domain.exception

import java.io.Serial

class IncorrectPasswordException  : RuntimeException {

    @Serial
    private val serialVersionUID: Long = -4937536120992271478L

                    //상위 클래스의 생성자를 호출
    constructor() : super("Incorrect Password")
    constructor(location : String) : super("Incorrect Password in [$location]")
    constructor(action:String, location : String) : super("Incorrect Password for [$action] in [$location]")
}