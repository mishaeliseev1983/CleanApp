package com.eliseev.cleanapp.presentation.viewhistoryprices

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.eliseev.cleanapp.R
import com.google.android.flexbox.FlexLine
import com.google.android.flexbox.FlexboxLayoutManager

class HistoryPrices @JvmOverloads internal constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    var fixMaxLine = 2
    var showAll = false
    lateinit var recyclerView: RecyclerView

    init {
        inflate(context, R.layout.history_prices_layout, this)
    }

    fun initHistoryPrices(prices: List<String>){
        initButton()
        initRecycler(prices =  prices)
    }

    private fun initButton() {
        val buttonShowMore: Button = findViewById(R.id.showMoreButton)
        buttonShowMore.setOnClickListener {
            showAll = !showAll
            if(showAll)
                buttonShowMore.text = context.getString(R.string.hide)
            else
                buttonShowMore.text = context.getString(R.string.show_more)
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }
    private fun initRecycler(prices: List<String>) {
        val pricesAdapter = PricesAdapter(prices)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager =
            object : FlexboxLayoutManager(context) {
                override fun getFlexLinesInternal(): MutableList<FlexLine> {
                    val originList = super.getFlexLinesInternal()
                    val size = originList.size
                    if (size > fixMaxLine && !showAll) {
                        originList.subList(fixMaxLine, size).clear()
                    }
                    return originList
                }
            }
        recyclerView.adapter = pricesAdapter
    }
}