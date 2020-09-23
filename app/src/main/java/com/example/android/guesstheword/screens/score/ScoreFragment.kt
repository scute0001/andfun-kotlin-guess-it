/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.ScoreFragmentBinding

/**
 * Fragment where the final score is shown, after the game is over
 */
class ScoreFragment : Fragment() {
    lateinit var viewModleFactiory: ScoreViewModelFactory
    lateinit var viewModle: ScoreViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class.
        val binding: ScoreFragmentBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.score_fragment,
                container,
                false
        )


        // TODO (04) Create and construct a ScoreViewModelFactory
        // TODO (05) Create ScoreViewModel by using ViewModelProvider as usual, except also
        // pass in your ScoreViewModelFactory
        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()
//        viewModleFactiory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).score)
        viewModleFactiory = ScoreViewModelFactory(scoreFragmentArgs.score)
        viewModle = ViewModelProvider(this, viewModleFactiory).get(ScoreViewModel::class.java)



        binding.playAgainButton.setOnClickListener {
            viewModle.onPlayAgain()
        }

        // TODO (07) Convert this class to properly observe and use ScoreViewModel
        viewModle.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })

        viewModle.eventPlayAgain.observe(viewLifecycleOwner, Observer { newPlayAgain ->
            if (newPlayAgain == true) {
                findNavController().navigate(R.id.action_restart)
            }
        })

        return binding.root
    }


}
