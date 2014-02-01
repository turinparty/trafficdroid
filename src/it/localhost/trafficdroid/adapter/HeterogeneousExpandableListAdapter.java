package it.localhost.trafficdroid.adapter;

import it.localhost.trafficdroid.common.Utility;

import java.util.ArrayList;

import localhost.widget.HeterogeneousItem;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class HeterogeneousExpandableListAdapter extends BaseExpandableListAdapter {
	private ArrayList<HeterogeneousItem> groupItems;
	private ArrayList<ArrayList<HeterogeneousItem>> childItems;
	private Context context;
	private ArrayList<Integer> types;

	public HeterogeneousExpandableListAdapter(Context context, ArrayList<HeterogeneousItem> groupItems, ArrayList<ArrayList<HeterogeneousItem>> childItems) {
		this.context = context;
		this.groupItems = groupItems;
		this.childItems = childItems;
		types = new ArrayList<Integer>();
		for (HeterogeneousItem abstractItem : groupItems)
			types.add(abstractItem.getClass().hashCode());
		for (ArrayList<HeterogeneousItem> childData : childItems)
			for (HeterogeneousItem abstractItem : childData)
				types.add(abstractItem.getClass().hashCode());
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		HeterogeneousItem rowModel = getGroup(groupPosition);
		if (convertView == null)
			convertView = rowModel.inflate();
		rowModel.fill(convertView);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		HeterogeneousItem rowModel = getChild(groupPosition, childPosition);
		if (convertView == null)
			convertView = rowModel.inflate();
		rowModel.fill(convertView);
		return convertView;
	}

	@Override
	public HeterogeneousItem getGroup(int groupPosition) {
		return groupItems.get(groupPosition);
	}

	@Override
	public HeterogeneousItem getChild(int groupPosition, int childPosition) {
		return childItems.get(groupPosition).get(childPosition);
	}

	@Override
	public int getGroupCount() {
		return groupItems.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return childItems.get(groupPosition).size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public int getChildType(int groupPosition, int childPosition) {
		return types.indexOf(getChild(groupPosition, childPosition).getClass().hashCode());
	}

	@Override
	public int getChildTypeCount() {
		return types.size();
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
		Utility.setExpanded(context, groupPosition, false);
	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		super.onGroupExpanded(groupPosition);
		Utility.setExpanded(context, groupPosition, true);
	}
}