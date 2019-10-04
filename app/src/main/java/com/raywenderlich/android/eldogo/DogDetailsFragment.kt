
package com.raywenderlich.android.eldogo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.android.eldogo.databinding.FragmentDogDetailsBinding


//1
class DogDetailsFragment : Fragment() {

  //2
  companion object {

    private const val DOGMODEL = "model"

    fun newInstance(dogModel: DogModel): DogDetailsFragment {
      val args = Bundle()
      args.putSerializable(DOGMODEL, dogModel)
      val fragment = DogDetailsFragment()
      fragment.arguments = args
      return fragment
    }
  }

  //3
  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    // 1
    val fragmentDogDetailsBinding =
      FragmentDogDetailsBinding.inflate(inflater, container, false)

// 2
    val model = arguments!!.getSerializable(DOGMODEL) as DogModel
// 3
    fragmentDogDetailsBinding.dogModel = model
    model.text = String.format(getString(R.string.description_format),
      model.description, model.url)
    return fragmentDogDetailsBinding.root
  }

}
