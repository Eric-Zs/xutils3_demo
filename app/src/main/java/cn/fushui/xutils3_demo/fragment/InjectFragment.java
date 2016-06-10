package cn.fushui.xutils3_demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.fushui.xutils3_demo.R;

/**
 * Created by jianfeng on 2016/6/8.
 */
@ContentView(R.layout.inject_fragment)
public class InjectFragment extends Fragment {

    @ViewInject(R.id.id_lv)
    ListView listView;

    @ViewInject(R.id.id_bt)
    Button button;
    List<String> stringList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
        listView.setAdapter(new MyAdapter());

    }


    //修饰符必须是private，可以不写
    //   @ViewInject(R.id.id_bt)
    //    Button button;
    //返回值为相关事件的返回值，参数也为对应事件的参数
    @Event(type = View.OnClickListener.class, value = R.id.id_bt)
    private void testButton(View v){
        Toast.makeText(getContext(), "haha", Toast.LENGTH_LONG).show();
    }

    @Event(type = View.OnLongClickListener.class, value = R.id.id_bt)
    private boolean testButtonLong(View v){
        Toast.makeText(getContext(), "long", Toast.LENGTH_LONG).show();
        return true;
    }

    @Event(type = AdapterView.OnItemClickListener.class, value = R.id.id_lv)
    private void testListView(AdapterView<?> parent, View view, int position, long id){
        Toast.makeText(getContext(), stringList.get(position), Toast.LENGTH_LONG).show();
    }
    private void getData() {
        stringList = new ArrayList<String>();
        for(int i = 0; i < 10; i ++){
            stringList.add("item" + i);
        }
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return stringList.size();
        }

        @Override
        public Object getItem(int position) {
            return stringList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView == null){
                convertView = View.inflate(getActivity().getApplicationContext(),R.layout.item_list,null);
                holder = new ViewHolder();
                //等价于在convertView实现holder的findViewById()
                x.view().inject(holder,convertView);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }

            holder.tv_item.setText(stringList.get(position));

            return convertView;
        }

        class ViewHolder{
            @ViewInject(R.id.id_tv_item)
            TextView tv_item;
        }
    }
}
