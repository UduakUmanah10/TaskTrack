
import com.example.tasktrack.login.domain.model.LoginResults

@Suppress("UnusedPrivateMember")
@JvmInline
value class Email(private val email: String)

@Suppress("UnusedPrivateMember")
@JvmInline
value class Password(private val email: String)

interface LoginUseCase {
    /**
     * This function consumes a :
     * @param[email]
     * @param[password]
     * @return[LoginResults] that contains a LoginResponse if successful or an Error if not
     **/

    suspend operator fun invoke(
        email: Email,
        password: Password
    ): LoginResults
}
