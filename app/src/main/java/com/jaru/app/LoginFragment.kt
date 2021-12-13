package com.jaru.app

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.jaru.app.databinding.FragmentLoginBinding
import com.jaru.app.viewModel.LoginVieModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val viewModel: LoginVieModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener { button -> onLoginClick(button) }
        addLiveDataObserver()


    }

    private fun addLiveDataObserver() {
        viewModel.LoginRequesResultLiveData.observe(viewLifecycleOwner,{isSuccessful ->
            binding.progressBar.visibility = View.GONE
            binding.buttonLogin.isEnabled = true


        })
    }

    private fun onLoginClick(button: View) {

        if (validLoginFormField()){
            button.isEnabled = false
            binding.progressBar.visibility = View.VISIBLE
            viewModel.login(binding.EmailAddress.text.toString(),binding.Password.text.toString())

        }
        // findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

    }


    private fun validLoginFormField(): Boolean {
        if (binding.EmailAddress.text.isEmpty() ||
            !Patterns.EMAIL_ADDRESS.matcher(binding.EmailAddress.text).matches()){
            binding.EmailAddress.error = getString(R.string.invalid_email_message)
            return false
        }else{
            binding.EmailAddress.error = null

        }
        if (binding.Password.text.isEmpty()){
            binding.Password.error = getString(R.string.field_empy_error)
            return false
        }else{
            binding.Password.error = null
        }

        return true

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}