package com.olejnikov.testovoe.mylibrary.presentation.ui.fragment.signIn

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.core.net.toUri
import com.olejnikov.testovoe.mylibrary.presentation.Constants
import com.olejnikov.testovoe.mylibrary.presentation.R
import com.olejnikov.testovoe.mylibrary.presentation.databinding.FragmentSignInBinding
import com.olejnikov.testovoe.mylibrary.presentation.global.changeColorAndAddActionForChapterOf
import com.olejnikov.testovoe.mylibrary.presentation.global.openUrl
import com.olejnikov.testovoe.mylibrary.presentation.ui.fragment.BaseFragment
import com.olejnikov.testovoe.mylibrary.presentation.ui.navigation.Screens

class SignInFragment : BaseFragment<Screens.SignIn, FragmentSignInBinding>(
    FragmentSignInBinding::inflate,
    Screens.SignIn
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBaseScreenState()
        setUpListeners()
    }

    private fun setUpBaseScreenState() = with(binding) {
        email.setUpOnlyEmail()
        checkValidnessForms()
    }

    private fun setUpListeners() = with(binding){
        noAccount.changeColorAndAddActionForChapterOf(R.string.noAccount, color = R.color.appGreen) {
            //TODO какая-то логика в будущем
        }
        forgetPassword.setOnClickListener {
            //TODO какая-то логика в будущем
        }
        socialNetworkCart.onClickVk {
            context?.openUrl(Constants.Content.VK_LINK.toUri())
        }
        socialNetworkCart.onClickClassmate {
            context?.openUrl(Constants.Content.CLASSMATE_LINK.toUri())
        }
        buttonEnter.setOnClickListener {
            navVm.forward(Screens.Main)
        }
        email.doAfterTextChange (::checkValidnessForms)
        password.doAfterTextChange(::checkValidnessForms)

    }

    private fun checkValidnessForms() = with(binding) {
        buttonEnter.isEnabled = Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()
                && password.getText().isNotEmpty()
    }



}