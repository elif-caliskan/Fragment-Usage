
package com.raywenderlich.android.eldogo

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.android.eldogo.databinding.RecyclerItemDogModelBinding
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager

class DogListFragment : Fragment() {

  private lateinit var imageResIds: IntArray
  private lateinit var names: Array<String>
  private lateinit var descriptions: Array<String>
  private lateinit var urls: Array<String>
  private lateinit var listener: OnDogSelected

  companion object {

    fun newInstance(): DogListFragment {
      return DogListFragment()
    }
  }

  internal inner class DogListAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
      val recyclerDogModelBinding =
          RecyclerItemDogModelBinding.inflate(layoutInflater, viewGroup, false)
      return ViewHolder(recyclerDogModelBinding.root, recyclerDogModelBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
      val dog = DogModel(imageResIds[position], names[position],
          descriptions[position], urls[position])
      viewHolder.setData(dog)
      viewHolder.itemView.setOnClickListener {
        listener.onDogSelected(dog)
      }
    }

    override fun getItemCount() = names.size
  }

  internal inner class ViewHolder constructor(itemView: View,
                                              private val recyclerItemDogListBinding:
                                              RecyclerItemDogModelBinding
  ) :
      RecyclerView.ViewHolder(itemView) {

    fun setData(dogModel: DogModel) {
      recyclerItemDogListBinding.dogModel = dogModel
    }
  }
  override fun onAttach(context: Context?) {
    super.onAttach(context)
    if (context is OnDogSelected) {
      listener = context
    } else {
      throw ClassCastException(
        context.toString() + " must implement OnDogSelected.")
    }

    // Get dog names and descriptions.
    val resources = context.resources
    names = resources.getStringArray(R.array.names)
    descriptions = resources.getStringArray(R.array.descriptions)
    urls = resources.getStringArray(R.array.urls)

    // Get dog images.
    val typedArray = resources.obtainTypedArray(R.array.images)
    val imageCount = names.size
    imageResIds = IntArray(imageCount)
    for (i in 0 until imageCount) {
      imageResIds[i] = typedArray.getResourceId(i, 0)
    }
    typedArray.recycle()
  }
  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    val view: View = inflater.inflate(R.layout.fragment_dog_list, container,
      false)
    val activity = activity as Context
    val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
    recyclerView.layoutManager = GridLayoutManager(activity, 2)
    recyclerView.adapter = DogListAdapter(activity)
    return view
  }
  interface OnDogSelected {
    fun onDogSelected(dogModel: DogModel)
  }

}
