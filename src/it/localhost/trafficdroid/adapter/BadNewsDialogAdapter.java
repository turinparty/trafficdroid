package it.localhost.trafficdroid.adapter;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.AbstractActivity;
import it.localhost.trafficdroid.adapter.item.AbstractItem;
import it.localhost.trafficdroid.adapter.item.BadNewsDialogItem;
import it.localhost.trafficdroid.adapter.item.BannerDialogItem;
import it.localhost.trafficdroid.common.billing.IabHelper;
import it.localhost.trafficdroid.common.billing.IabHelper.OnIabSetupFinishedListener;
import it.localhost.trafficdroid.common.billing.IabHelper.QueryInventoryFinishedListener;
import it.localhost.trafficdroid.common.billing.IabResult;
import it.localhost.trafficdroid.common.billing.Inventory;
import it.localhost.trafficdroid.dto.BadNewsDTO;
import it.localhost.trafficdroid.dto.StreetDTO;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class BadNewsDialogAdapter extends ArrayAdapter<BadNewsDTO> implements QueryInventoryFinishedListener, OnIabSetupFinishedListener {
	private ArrayList<AbstractItem> items;
	private IabHelper mHelper;
	private Context context;

	public BadNewsDialogAdapter(Context context, StreetDTO street) {
		super(context, 0);
		this.context = context;
		mHelper = new IabHelper(context, AbstractActivity.KEY);
		mHelper.startSetup(this);
		items = new ArrayList<AbstractItem>();
		for (BadNewsDTO badNews : street.getBadNews())
			items.add(new BadNewsDialogItem(context, badNews));
	}

	@Override
	public void onIabSetupFinished(IabResult result) {
		if (result.isSuccess())
			mHelper.queryInventoryAsync(true, AbstractActivity.additionalSkuList, this);
	}

	@Override
	public void onQueryInventoryFinished(IabResult result, Inventory inv) {
		if (!result.isSuccess() || !inv.hasPurchase(AbstractActivity.SKU_AD_FREE)) {
			items.add(0, new BannerDialogItem(context, R.layout.iab_mrect));
			items.add(new BannerDialogItem(context, R.layout.iab_mrect));
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AbstractItem rowModel = items.get(position);
		if (convertView == null)
			convertView = rowModel.inflateView();
		rowModel.fillView(convertView);
		return convertView;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public int getItemViewType(int position) {
		return items.get(position).getType();
	}

	@Override
	public int getViewTypeCount() {
		return AbstractItem.itemTypes.length;
	}
}
