package it.localhost.trafficdroid.adapter;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.AbstractActivity;
import it.localhost.trafficdroid.adapter.item.AbstractItem;
import it.localhost.trafficdroid.adapter.item.BadNewsItem;
import it.localhost.trafficdroid.adapter.item.BannerDialogItem;
import it.localhost.trafficdroid.adapter.item.StreetItem;
import it.localhost.trafficdroid.adapter.item.ZoneItem;
import it.localhost.trafficdroid.common.TdApp;
import it.localhost.trafficdroid.common.billing.IabHelper;
import it.localhost.trafficdroid.common.billing.IabHelper.OnIabSetupFinishedListener;
import it.localhost.trafficdroid.common.billing.IabHelper.QueryInventoryFinishedListener;
import it.localhost.trafficdroid.common.billing.IabResult;
import it.localhost.trafficdroid.common.billing.Inventory;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class MainAdapter extends BaseExpandableListAdapter implements QueryInventoryFinishedListener, OnIabSetupFinishedListener {
	public static final String expanded = "Expanded";
	private ArrayList<AbstractItem> groupItems;
	private ArrayList<ArrayList<AbstractItem>> childItems;
	private IabHelper mHelper;
	private Context context;

	public MainAdapter(Context context, MainDTO mainDTO) {
		this.context = context;
		mHelper = new IabHelper(context, AbstractActivity.KEY);
		mHelper.startSetup(this);
		groupItems = new ArrayList<AbstractItem>();
		childItems = new ArrayList<ArrayList<AbstractItem>>();
		for (StreetDTO street : mainDTO.getStreets()) {
			groupItems.add(new StreetItem(context, street));
			ArrayList<AbstractItem> childItems = new ArrayList<AbstractItem>();
			childItems.add(new BadNewsItem(context, street));
			for (ZoneDTO zone : street.getZones())
				childItems.add(new ZoneItem(context, zone));
			this.childItems.add(childItems);
		}
	}

	@Override
	public void onIabSetupFinished(IabResult result) {
		if (result.isSuccess())
			mHelper.queryInventoryAsync(true, AbstractActivity.additionalSkuList, this);
	}

	@Override
	public void onQueryInventoryFinished(IabResult result, Inventory inv) {
		for (int i = 0; i < childItems.size(); i++) {
			int size = childItems.get(i).size();
			for (int j = 0; j < size; j++)
				if (Math.random() < 0.05 && (!result.isSuccess() || !inv.hasPurchase(AbstractActivity.SKU_AD_FREE))) {
					childItems.get(i).add(j++, new BannerDialogItem(context, R.layout.smart_banner));
					size++;
				}
		}
	}

	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		AbstractItem rowModel = groupItems.get(groupPosition);
		if (convertView == null)
			convertView = rowModel.inflateView();
		rowModel.fillView(convertView);
		return convertView;
	}

	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		AbstractItem rowModel = childItems.get(groupPosition).get(childPosition);
		if (convertView == null)
			convertView = rowModel.inflateView();
		rowModel.fillView(convertView);
		return convertView;
	}

	public Object getGroup(int groupPosition) {
		return groupItems.get(groupPosition);
	}

	public Object getChild(int groupPosition, int childPosition) {
		return childItems.get(groupPosition).get(childPosition);
	}

	public int getGroupCount() {
		return groupItems.size();
	}

	public int getChildrenCount(int groupPosition) {
		return childItems.get(groupPosition).size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public boolean hasStableIds() {
		return true;
	}

	@Override
	public int getChildType(int groupPosition, int childPosition) {
		return childItems.get(groupPosition).get(childPosition).getType();
	}

	@Override
	public int getChildTypeCount() {
		return AbstractItem.itemTypes.length;
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
		TdApp.getEditor().putBoolean(expanded + groupPosition, false).commit();
	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		super.onGroupExpanded(groupPosition);
		TdApp.getEditor().putBoolean(expanded + groupPosition, true).commit();
	}
}