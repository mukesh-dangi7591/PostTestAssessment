package com.posts.test.activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.posts.test.R


class DetailedFragment : Fragment() {
    companion object {
        private const val post_id = "id"
        private const val title = "title"
        private const val l_name = "l_name"
        private const val email = "email"

        fun newInstance(id: String,f_name: String,l_name: String,email: String,): DetailedFragment {
            val fragment = DetailedFragment()
            val args = Bundle()
            args.putString(post_id, id)
            args.putString(title, f_name)
            args.putString(this.l_name, l_name)
            args.putString(this.email, email)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var idText = view.findViewById<TextView>(R.id.postId)
        var titleText = view.findViewById<TextView>(R.id.postTitle)
        var lNameText = view.findViewById<TextView>(R.id.postlName)
        var emailText = view.findViewById<TextView>(R.id.postEmail)

        idText.text = "Post Id :- "+arguments?.getString(post_id);
        titleText.text = "Post first name :- "+arguments?.getString(Companion.title);
        lNameText.text = "Post last name :- "+arguments?.getString(l_name);
        emailText.text = "Post email :- "+arguments?.getString(email);
    }
}