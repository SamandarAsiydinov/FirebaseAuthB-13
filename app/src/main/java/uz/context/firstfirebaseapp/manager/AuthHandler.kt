package uz.context.firstfirebaseapp.manager

interface AuthHandler {
    fun onSuccess()
    fun onError(exception: Exception?)
}