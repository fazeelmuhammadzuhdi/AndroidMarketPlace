package com.example.marketplace.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marketplace.core.data.source.remote.network.State
import com.example.marketplace.core.data.source.remote.request.LoginRequest
import com.example.marketplace.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setData()
    }

    private fun setData() {
        viewModel.text.observe(this, {
            binding.edtEmail.setText(it)
        })

        binding.btnMasuk.setOnClickListener {
            login()
        }
    }

     private fun login(){
         if(binding.edtEmail.text!!.isEmpty()) return
         if(binding.edtPassword.text!!.isEmpty()) return

         val body = LoginRequest(
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString()
        )

        viewModel.login(body).observe(this, {

            when(it.state){
                State.SUCCESS ->{
                    binding.pd.visibility = View.GONE
                    Toast.makeText(this, "Selamat Datang" +it?.data?.name, Toast.LENGTH_SHORT).show()
                }
                State.ERROR->{
                    binding.pd.visibility = View.GONE
                    Toast.makeText(this, "Error" +it?.message, Toast.LENGTH_SHORT).show()
                }
                State.LOADING->{
                    binding.pd.visibility = View.VISIBLE
                }
            }

        })
    }

}