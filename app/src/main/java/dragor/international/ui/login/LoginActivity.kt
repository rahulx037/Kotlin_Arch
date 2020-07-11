package dragor.international.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import dragor.international.R
import dragor.international.api.Resource
import dragor.international.api.Status
import dragor.international.api.models.LoginRequest
import dragor.international.api.models.LoginResponse
import dragor.international.api.models.RegisterRequest
import dragor.international.api.models.RegisterResponse
import dragor.international.databinding.ActivityLoginBinding
import dragor.international.ui.MainActivity
import dragor.international.ui.base.BaseActivity
import dragor.international.util.AppUtils
import dragor.international.util.CommonEventHandler
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>(), CommonEventHandler {

    private val mLoginViewModel: LoginViewModel by viewModel()

    override fun getLayoutRes(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_FullScreen)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        super.onCreate(savedInstanceState)
        dataBinding.handler = this
        dataBinding.lifecycleOwner=this

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.email_sign_in_button -> {
                if (isValidateFields()) {
                    startActivity(Intent(this, MainActivity::class.java))

                    mLoginViewModel.doLogin(
                        LoginRequest(
                            dataBinding.email.text.toString().trim(),
                            dataBinding.password.text.toString().trim()
                        )
                    ).observe(this, this::handleLoginResponse)
                }

                makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()

            }


            R.id.forgotpassword -> {
                dataBinding.emailLoginForm.visibility = View.GONE
                dataBinding.forgotPasswordForm.visibility = View.VISIBLE
            }
            R.id.back_navigation -> {
                dataBinding.forgotPasswordForm.visibility = View.GONE
                dataBinding.emailLoginForm.visibility = View.VISIBLE
            }
            R.id.btn_signNow -> {
                dataBinding.emailLoginForm.visibility = View.GONE
                dataBinding.signupForm.visibility = View.VISIBLE
            }
            R.id.sign_up_navigation -> {
                dataBinding.signupForm.visibility = View.GONE
                dataBinding.emailLoginForm.visibility = View.VISIBLE
            }
            R.id.btn_signup -> {
                val register = RegisterRequest(dataBinding.name.text.toString(),
                    dataBinding.name.text.toString(),
                    (if (dataBinding.roleSpinner.isChecked) "1" else "2"),
                    dataBinding.signupEmail.text.toString(),
                    dataBinding.signupEmail.text.toString(),
                    dataBinding.signupPassword.text.toString(),
                    dataBinding.signupPassword.text.toString(),
                    dataBinding.signupEmail.text.toString()
                )
                mLoginViewModel.registerUser(register).observe(this,this::handleRegisterResponse)
            }
        }
    }


    private fun handleLoginResponse(resourceResource: Resource<Resource<LoginResponse>>) {
        if(resourceResource.status !=null || resourceResource.data != null) {
            when (resourceResource.status) {
                Status.LOADING -> {
                    makeText(applicationContext, "Loading", Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()

                }
                Status.ERROR -> {
                    makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
    private fun handleRegisterResponse(resourceResource: Resource<Resource<RegisterResponse>>) {
        when (resourceResource.status) {
            Status.LOADING -> {}
            Status.SUCCESS -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            Status.ERROR -> {}
        }
    }


    private fun isValidateFields(): Boolean {
        if (dataBinding.email.text.toString().trim().isEmpty()) {
            dataBinding.email.requestFocus()
            dataBinding.email.error = getString(R.string.error_email)
            AppUtils.bounceAnimate(dataBinding.email)
            return false
        } /*else if (!Validator.isValidEmail(dataBinding.email.getText().toString())) {
            dataBinding.email.requestFocus();
            dataBinding.email.setError(getString(R.string.error_invalid_email));
            AppUtils.bounceAnimate(dataBinding.email);
            return false;
        }*/ else if (TextUtils.isEmpty(dataBinding.password.text.toString())) {
            dataBinding.password.requestFocus()
            dataBinding.password.error = getString(R.string.error_invalid_password)
            AppUtils.bounceAnimate(dataBinding.password)
            return false
        }
        return true
    }


}
