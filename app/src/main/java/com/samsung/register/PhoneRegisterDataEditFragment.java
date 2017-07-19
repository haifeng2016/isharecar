package com.samsung.register;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.samsung.data.BaseData;
import com.samsung.data.Contact;
import com.samsung.data.SQLManager;
import com.samsung.isharecar.FragmentBase;
import com.samsung.isharecar.MainActivity;
import com.samsung.isharecar.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PhoneRegisterDataEditFragment extends FragmentBase implements AdapterView.OnItemClickListener {

    private View view;
    private LinearLayout head;
    private LinearLayout nickname;
    private LinearLayout age;
    private LinearLayout job;
    private LinearLayout signature;
    private LinearLayout sex;
    private TextView hometown;
    private TextView tvNickName = null;
    private TextView tvSex = null;
    private TextView tvAge = null;
    private TextView tvJob = null;
    private TextView tvSig = null;
    private TextView tvHometown = null;
    

    Dialog dialog;
    ListView mlistView,mlistView2;
    BaseAdapter adapter;
    private BaseData mBaseData = null;
    private Contact mcontact = null;
    private List<Contact> contactList = null;

    public String provinceName="";
    private int selectedFruitIndex = 0;
    private int selectedAgeIndex = 0;
    private static int CAMERA_REQUEST_CODE = 1;
    private static int GALLERY_REQUEST_CODE = 2;
    private static int CROP_REQUEST_CODE = 3;

    List<String> list1=new ArrayList<String>();
    List<String> list2=new ArrayList<String>();
    List<String> list3=new ArrayList<String>();
    List<String> list4=new ArrayList<String>();
    List<String> list5=new ArrayList<String>();
    List<String> list6=new ArrayList<String>();
    List<String> list7=new ArrayList<String>();
    List<String> list8=new ArrayList<String>();
    List<String> list9=new ArrayList<String>();
    List<String> list10=new ArrayList<String>();
    List<String> list11=new ArrayList<String>();
    List<String> list12=new ArrayList<String>();
    List<String> list13=new ArrayList<String>();
    List<String> list14=new ArrayList<String>();
    List<String> list15=new ArrayList<String>();
    List<String> list16=new ArrayList<String>();
    List<String> list17=new ArrayList<String>();
    List<String> list18=new ArrayList<String>();
    List<String> list19=new ArrayList<String>();
    List<String> list20=new ArrayList<String>();
    List<String> list21=new ArrayList<String>();
    List<String> list22=new ArrayList<String>();
    List<String> list23=new ArrayList<String>();
    List<String> list24=new ArrayList<String>();
    List<String> list25=new ArrayList<String>();
    List<String> list26=new ArrayList<String>();
    List<String> list27=new ArrayList<String>();
    List<String> list28=new ArrayList<String>();
    List<String> list29=new ArrayList<String>();
    List<String> list30=new ArrayList<String>();
    List<String> list31=new ArrayList<String>();
    List<String> list32=new ArrayList<String>();
    List<String> list33=new ArrayList<String>();
    List<String> list34=new ArrayList<String>();
    List<String> list35=new ArrayList<String>();
    List<String> list36=new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phoneregister_dataedit, container,false);

        //���ñ�����
        dataEditActivity.childViewActionBarStyle(R.string.data_edit);
        
        head = (LinearLayout) view.findViewById(R.id.head);
        nickname = (LinearLayout) view.findViewById(R.id.nickname);
        
        if(MainActivity.mDebug)
            //get the data from SQLite
            mBaseData = new SQLManager(getActivity());
        else{
            //get the date from network
            mBaseData = new SQLManager(getActivity());
        }
        if(MainActivity.getUserPhone() != null){
            contactList = mBaseData.searchByContactColumn(getActivity(),BaseData.USER_COLUM_PHONE,MainActivity.getUserPhone());
            if(contactList != null)
                mcontact = contactList.get(0);
        }
        initValue();
        
        age = (LinearLayout) view.findViewById(R.id.age);
        job = (LinearLayout) view.findViewById(R.id.job);
        signature = (LinearLayout) view.findViewById(R.id.signature);
        sex = (LinearLayout) view.findViewById(R.id.sex);
        hometown = (TextView) view.findViewById(R.id.hometown);
        head.setOnClickListener(actionClickedListener);
        nickname.setOnClickListener(actionClickedListener);
        age.setOnClickListener(actionClickedListener);
        job.setOnClickListener(actionClickedListener);
        signature.setOnClickListener(actionClickedListener);
        sex.setOnClickListener(actionClickedListener);
        hometown.setOnClickListener(actionClickedListener);

        list1.add("北京市");list1.add("天津市");list1.add("河北省");list1.add("山西省");list1.add("内蒙古");list1.add("辽宁省");
        list1.add("吉林省");list1.add("黑龙江");list1.add("上海市");list1.add("江苏省");list1.add("浙江省");list1.add("安徽省");
        list1.add("福建省");list1.add("江西省");list1.add("山东省");list1.add("河南省");list1.add("湖北省");list1.add("湖南省");
        list1.add("广东省");list1.add("广西");list1.add("海南省");list1.add("重庆市");list1.add("四川省");list1.add("贵州省");
        list1.add("云南省");list1.add("西藏");list1.add("陕西省");list1.add("甘肃省");list1.add("青海省");list1.add("宁夏");
        list1.add("新疆");list1.add("香港");list1.add("澳门");list1.add("台湾省");

        list2.add("东城区");list2.add("西城区");list2.add("崇文区");list2.add("宣武区");list2.add("朝阳区");list2.add("丰台区");
        list2.add("石景山区");list2.add("海淀区");list2.add("门头沟区");list2.add("房山区");list2.add("通州区");list2.add("顺义区");
        list2.add("昌平区");list2.add("大兴区");list2.add("怀柔区");list2.add("平谷区");

        list3.add("和平区");list3.add("河东区");list3.add("河西区");list3.add("南开区");list3.add("河北区");list3.add("红桥区");
        list3.add("塘沽区");list3.add("汉沽区");list3.add("大港区");list3.add("东丽区");list3.add("西青区");list3.add("津南区");
        list3.add("北辰区");list3.add("武清区");list3.add("宝坻区");

        list4.add("石家庄");list4.add("唐山");list4.add("秦皇岛");list4.add("邯郸");list4.add("邢台");list4.add("保定");
        list4.add("张家口");list4.add("承德");list4.add("沧州");list4.add("廊坊");list4.add("衡水");

        list5.add("太原");list5.add("大同");list5.add("阳泉");list5.add("长治");list5.add("晋城");list5.add("朔州");
        list5.add("晋中");list5.add("运城");list5.add("忻州");list5.add("临汾");list5.add("吕梁");

        list6.add("呼和浩特");list6.add("包头");list6.add("乌海");list6.add("赤峰");list6.add("通辽");list6.add("鄂尔多斯");
        list6.add("呼伦贝尔");list6.add("巴彦淖尔");list6.add("乌兰察布");list6.add("兴安盟");list6.add("锡林郭勒盟");list6.add("阿拉善盟");

        list7.add("沈阳");list7.add("大连");list7.add("鞍山");list7.add("抚顺");list7.add("本溪");list7.add("丹东");
        list7.add("锦州");list7.add("营口");list7.add("阜新");list7.add("辽阳");list7.add("盘锦");list7.add("铁岭");
        list7.add("朝阳");list7.add("葫芦岛");

        list8.add("长春");list8.add("吉林");list8.add("四平");list8.add("辽源");list8.add("通化");list8.add("白山");
        list8.add("松原");list8.add("白城");list8.add("延边");

        list9.add("哈尔滨");list9.add("齐齐哈尔");list9.add("鸡西");list9.add("鹤岗");list9.add("双鸭山");list9.add("大庆");
        list9.add("伊春");list9.add("佳木斯");list9.add("七台河");list9.add("牡丹江");list9.add("黑河");list9.add("绥化");
        list9.add("大兴安岭");

        list10.add("黄浦区");list10.add("卢湾区");list10.add("徐汇区");list10.add("长宁区");list10.add("静安区");list10.add("普陀区");
        list10.add("闸北区");list10.add("虹口区");list10.add("杨浦区");list10.add("闵行区");list10.add("宝山区");list10.add("嘉定区");
        list10.add("浦东新区");list10.add("金山区");list10.add("松江区");list10.add("青浦区");list10.add("南汇区");list10.add("奉贤区");

        list11.add("南京");list11.add("无锡");list11.add("徐州");list11.add("常州");list11.add("苏州");list11.add("南通");
        list11.add("连云港");list11.add("淮安");list11.add("盐城");list11.add("扬州");list11.add("镇江");list11.add("泰州");
        list11.add("宿迁");

        list12.add("杭州");list12.add("宁波");list12.add("温州");list12.add("嘉兴");list12.add("湖州");list12.add("绍兴");
        list12.add("金华");list12.add("衢州");list12.add("舟山");list12.add("台州");list12.add("丽水");

        list13.add("合肥");list13.add("芜湖");list13.add("蚌埠");list13.add("淮南");list13.add("马鞍山");list13.add("淮北");
        list13.add("铜陵");list13.add("安庆");list13.add("黄山");list13.add("滁州");list13.add("阜阳");list13.add("宿州");
        list13.add("巢湖");list13.add("六安");list13.add("亳州");list13.add("池州");list13.add("宣城");

        list14.add("福州");list14.add("厦门");list14.add("莆田");list14.add("三明");list14.add("泉州");list14.add("漳州");
        list14.add("南平");list14.add("龙岩");list14.add("宁德");

        list15.add("南昌");list15.add("景德镇");list15.add("萍乡");list15.add("九江");list15.add("新余");list15.add("鹰潭");
        list15.add("赣州");list15.add("吉安");list15.add("宜春");list15.add("抚州");list15.add("上饶");

        list16.add("济南");list16.add("青岛");list16.add("淄博");list16.add("枣庄");list16.add("东营");list16.add("烟台");
        list16.add("潍坊");list16.add("济宁");list16.add("泰安");list16.add("威海");list16.add("日照");list16.add("莱芜");
        list16.add("临沂");list16.add("德州");list16.add("聊城");list16.add("滨州");list16.add("荷泽");

        list17.add("郑州");list17.add("开封");list17.add("洛阳");list17.add("平顶山");list17.add("安阳");list17.add("鹤壁");
        list17.add("新乡");list17.add("焦作");list17.add("濮阳");list17.add("许昌");list17.add("漯河");list17.add("三门峡");
        list17.add("南阳");list17.add("商丘");list17.add("信阳");list17.add("周口");list17.add("驻马店");

        list18.add("武汉");list18.add("黄石");list18.add("十堰");list18.add("宜昌");list18.add("襄樊");list18.add("鄂州");
        list18.add("荆门");list18.add("孝感");list18.add("荆州");list18.add("黄冈");list18.add("咸宁");list18.add("随州");
        list18.add("恩施");list18.add("神农架");

        list19.add("长沙");list19.add("株洲");list19.add("湘潭");list19.add("衡阳");list19.add("邵阳");list19.add("常德");
        list19.add("张家界");list19.add("益阳");list19.add("郴州");list19.add("永州");list19.add("怀化");list19.add("娄底");
        list19.add("湘西");

        list20.add("广州");list20.add("韶关");list20.add("深圳");list20.add("珠海");list20.add("汕头");list20.add("佛山");
        list20.add("江门");list20.add("湛江");list20.add("茂名");list20.add("肇庆");list20.add("惠州");list20.add("梅州");
        list20.add("汕尾");list20.add("河源");list20.add("阳江");list20.add("清远");list20.add("东莞");list20.add("中山");
        list20.add("潮州");list20.add("揭阳");list20.add("云浮");

        list21.add("南宁");list21.add("柳州");list21.add("桂林");list21.add("梧州");list21.add("北海");list21.add("防城港");
        list21.add("钦州");list21.add("贵港");list21.add("玉林");list21.add("百色");list21.add("贺州");list21.add("河池");
        list21.add("来宾");list21.add("崇左");

        list23.add("海口");list23.add("三亚");list23.add("三沙");

        list24.add("万州区");list24.add("涪陵区");list24.add("渝中区");list24.add("大渡口区");list24.add("江北区");list24.add("沙坪坝区");
        list24.add("九龙坡区");list24.add("南岸区");list24.add("北碚区");list24.add("万盛区");list24.add("双桥区");list24.add("渝北区");
        list24.add("巴南区");list24.add("黔江区");list24.add("长寿区");

        list25.add("成都");list25.add("自贡");list25.add("攀枝花");list25.add("泸州");list25.add("德阳");list25.add("绵阳");
        list25.add("广元");list25.add("遂宁");list25.add("内江");list25.add("乐山");list25.add("南充");list25.add("眉山");
        list25.add("宜宾");list25.add("广安");list25.add("达州");list25.add("雅安");list25.add("巴中");list25.add("资阳");
        list25.add("阿坝");list25.add("甘孜");list25.add("凉山");

        list26.add("贵阳");list26.add("六盘水");list26.add("遵义");list26.add("安顺");list26.add("铜仁");list26.add("黔西南");
        list26.add("毕节");list26.add("黔东南");list26.add("黔南");

        list27.add("昆明");list27.add("曲靖");list27.add("玉溪");list27.add("保山");list27.add("昭通");list27.add("丽江");
        list27.add("思茅");list27.add("临沧");list27.add("楚雄");list27.add("红河");list27.add("文山");list27.add("西双版纳");
        list27.add("大理");list27.add("德宏");list27.add("怒江");list27.add("迪庆");

        list28.add("拉萨");list28.add("昌都");list28.add("山南");list28.add("日喀则");list28.add("那曲");list28.add("阿里");
        list28.add("林芝");

        list29.add("西安");list29.add("铜川");list29.add("宝鸡");list29.add("咸阳");list29.add("渭南");list29.add("延安");
        list29.add("汉中");list29.add("榆林");list29.add("安康");list29.add("商洛");

        list30.add("兰州");list30.add("嘉峪关");list30.add("金昌");list30.add("白银");list30.add("天水");list30.add("武威");
        list30.add("张掖");list30.add("平凉");list30.add("酒泉");list30.add("庆阳");list30.add("定西");list30.add("陇南");
        list30.add("临夏");list30.add("甘南");

        list31.add("西宁");list31.add("海东");list31.add("海北");list31.add("黄南");list31.add("海南");list31.add("果洛");
        list31.add("玉树");list31.add("海西");

        list32.add("银川");list32.add("石嘴山");list32.add("吴忠");list32.add("固原");list32.add("中卫");

        list33.add("乌鲁木齐");list33.add("克拉玛依");list33.add("吐鲁番");list33.add("哈密");list33.add("昌吉");list33.add("博尔塔拉");
        list33.add("巴音郭楞");list33.add("阿克苏");list33.add("克孜勒苏");list33.add("喀什");list33.add("和田");list33.add("伊犁");
        list33.add("塔城");list33.add("阿勒泰");list33.add("石河子");list33.add("阿拉尔");list33.add("图木舒克");list33.add("五家渠");

        list34.add("香港");

        list35.add("澳门");

        list36.add("台湾");

        return view;
    }
    
    private void initValue(){
        /***init nick name and sex by contact object***/
        tvNickName = (TextView) view.findViewById(R.id.nicknameinfo);
        if(tvNickName != null)
            tvNickName.setText(dataEditActivity.contact.get_name());
        
        tvSex = (TextView) view.findViewById(R.id.sexinfo);
        if(tvSex != null)
            tvSex.setText(dataEditActivity.contact.getSex());
        
        tvAge = (TextView) view.findViewById(R.id.ageinfo);
        if((tvAge != null) && (mcontact != null))
            tvAge.setText(mcontact.getAge());
        
        tvJob = (TextView)view.findViewById(R.id.jobinfo);
        if((tvJob != null) && (mcontact != null))
            tvJob.setText(mcontact.getOccupation());
        
        tvSig = (TextView)view.findViewById(R.id.signatureinfo);
        if((tvSig != null) && (mcontact != null))
            tvSig.setText(mcontact.getSignature());
        
        tvHometown = (TextView)view.findViewById(R.id.hometown);
        if((tvHometown != null) && (mcontact != null))
            tvHometown.setText(mcontact.get_hometown());
    
        
    }

    public void showDialog2(String title,List<String> list){

        AlertDialog.Builder builder = new Builder(getActivity());

        builder.setTitle(title);

        final LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.fragment_phoneregister_hometown_dialog_item, null);

        adapter  = new MyAdapter(getActivity(), list);

        mlistView = (ListView)v.findViewById(R.id.dialoglist);
        mlistView2 = (ListView)v.findViewById(R.id.dialoglist2);

        if(title=="点击选取省份"){
            mlistView2.setVisibility(View.GONE);
            mlistView.setAdapter(adapter);
            mlistView.setOnItemClickListener(this);
        }
        else
        {
            mlistView.setVisibility(View.GONE);
            mlistView2.setAdapter(adapter);
            mlistView2.setOnItemClickListener(this);
        }

        builder.setView(v);
        builder.setNegativeButton("取消", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    public class MyAdapter extends BaseAdapter {
        String mg;

        private ListView father;
        List<String> list;

        private LayoutInflater mInflater;

        public MyAdapter(Context context,List<String> list) {
            this.mInflater = LayoutInflater.from(context);
            this.list=list;
        }

        public int getCount() {
            return list.size();
        }

        public Object getItem(int position) {
            return list.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public boolean isEnabled(int position) {

            if (getItemViewType(position) == 1) {
                return false;
            }
            return super.isEnabled(position);

        }

        public View getView(final int position, View convertView,
                ViewGroup parent) {
            father = (ListView) parent;
            convertView = mInflater.inflate(R.layout.fragment_phoneregister_hometown_listitem, null);

            TextView tv_adress = (TextView) convertView.findViewById(R.id.listtext);;
            tv_adress.setText(list.get(position));

            return convertView;
        }

        public void onScroll(AbsListView view, int firstVisibleItem,
                int visibleItemCount, int totalItemCount) {
        }

        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (scrollState != OnScrollListener.SCROLL_STATE_IDLE) {
                isIdle = false;
            } else {
                isIdle = true;
                notifyDataSetChanged();
            }
        }
        private volatile boolean isIdle = true;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        String tv1="";
        String tv2;
        switch (arg0.getId()) {
        case R.id.dialoglist:
            provinceName=tv1=list1.get(arg2);
            dialog.dismiss();
            if(tv1.equals("北京市"))
                showDialog2("点击选取城市", list2);
            else if(tv1.equals("天津市"))
                showDialog2("点击选取城市", list3);
            else if(tv1.equals("河北省"))
                showDialog2("点击选取城市", list4);
            else if(tv1.equals("山西省"))
                showDialog2("点击选取城市", list5);
            else if(tv1.equals("内蒙古"))
                showDialog2("点击选取城市", list6);
            else if(tv1.equals("辽宁省"))
                showDialog2("点击选取城市", list7);
            else if(tv1.equals("吉林省"))
                showDialog2("点击选取城市", list8);
            else if(tv1.equals("黑龙江"))
                showDialog2("点击选取城市", list9);
            else if(tv1.equals("上海市"))
                showDialog2("点击选取城市", list10);
            else if(tv1.equals("江苏省"))
                showDialog2("点击选取城市", list11);
            else if(tv1.equals("浙江省"))
                showDialog2("点击选取城市", list12);
            else if(tv1.equals("安徽省"))
                showDialog2("点击选取城市", list13);
            else if(tv1.equals("福建省"))
                showDialog2("点击选取城市", list14);
            else if(tv1.equals("江西省"))
                showDialog2("点击选取城市", list15);
            else if(tv1.equals("山东省"))
                showDialog2("点击选取城市", list16);
            else if(tv1.equals("河南省"))
                showDialog2("点击选取城市", list17);
            else if(tv1.equals("湖北省"))
                showDialog2("点击选取城市", list18);
            else if(tv1.equals("湖南省"))
                showDialog2("点击选取城市", list19);
            else if(tv1.equals("广东省"))
                showDialog2("点击选取城市", list20);
            else if(tv1.equals("广西"))
                showDialog2("点击选取城市", list21);
            else if(tv1.equals("海南省"))
                showDialog2("点击选取城市", list23);
            else if(tv1.equals("重庆市"))
                showDialog2("点击选取城市", list24);
            else if(tv1.equals("四川省"))
                showDialog2("点击选取城市", list25);
            else if(tv1.equals("贵州省"))
                showDialog2("点击选取城市", list26);
            else if(tv1.equals("云南省"))
                showDialog2("点击选取城市", list27);
            else if(tv1.equals("西藏"))
                showDialog2("点击选取城市", list28);
            else if(tv1.equals("陕西省"))
                showDialog2("点击选取城市", list29);
            else if(tv1.equals("甘肃省"))
                showDialog2("点击选取城市", list30);
            else if(tv1.equals("青海省"))
                showDialog2("点击选取城市", list31);
            else if(tv1.equals("宁夏"))
                showDialog2("点击选取城市", list32);
            else if(tv1.equals("新疆"))
                showDialog2("点击选取城市", list33);
            else if(tv1.equals("香港"))
                showDialog2("点击选取城市", list34);
            else if(tv1.equals("澳门"))
                showDialog2("点击选取城市", list35);
            else if(tv1.equals("台湾省"))
                showDialog2("点击选取城市", list36);
            else{
                //Toast.makeText(this, "没有匹配的城市", Toast.LENGTH_SHORT).show();
                hometown.setText("请选择城市");
            }
            break;
            case R.id.dialoglist2:
                if(provinceName.equals("北京市"))
                    tv2=list2.get(arg2);
                else if(provinceName.equals("天津市"))
                    tv2=list3.get(arg2);
                else if(provinceName.equals("河北省"))
                    tv2=list4.get(arg2);
                else if(provinceName.equals("山西省"))
                    tv2=list5.get(arg2);
                else if(provinceName.equals("内蒙古"))
                    tv2=list6.get(arg2);
                else if(provinceName.equals("辽宁省"))
                    tv2=list7.get(arg2);
                else if(provinceName.equals("吉林省"))
                    tv2=list8.get(arg2);
                else if(provinceName.equals("黑龙江"))
                    tv2=list9.get(arg2);
                else if(provinceName.equals("上海市"))
                    tv2=list10.get(arg2);
                else if(provinceName.equals("江苏省"))
                    tv2=list11.get(arg2);
                else if(provinceName.equals("浙江省"))
                    tv2=list12.get(arg2);
                else if(provinceName.equals("安徽省"))
                    tv2=list13.get(arg2);
                else if(provinceName.equals("福建省"))
                    tv2=list14.get(arg2);
                else if(provinceName.equals("江西省"))
                    tv2=list15.get(arg2);
                else if(provinceName.equals("山东省"))
                    tv2=list16.get(arg2);
                else if(provinceName.equals("河南省"))
                    tv2=list17.get(arg2);
                else if(provinceName.equals("湖北省"))
                    tv2=list18.get(arg2);
                else if(provinceName.equals("湖南省"))
                    tv2=list19.get(arg2);
                else if(provinceName.equals("广东省"))
                    tv2=list20.get(arg2);
                else if(provinceName.equals("广西"))
                    tv2=list21.get(arg2);
                else if(provinceName.equals("海南省"))
                    tv2=list23.get(arg2);
                else if(provinceName.equals("重庆市"))
                    tv2=list24.get(arg2);
                else if(provinceName.equals("四川省"))
                    tv2=list25.get(arg2);
                else if(provinceName.equals("贵州省"))
                    tv2=list26.get(arg2);
                else if(provinceName.equals("云南省"))
                    tv2=list27.get(arg2);
                else if(provinceName.equals("西藏"))
                    tv2=list28.get(arg2);
                else if(provinceName.equals("陕西省"))
                    tv2=list29.get(arg2);
                else if(provinceName.equals("甘肃省"))
                    tv2=list30.get(arg2);
                else if(provinceName.equals("青海省"))
                    tv2=list31.get(arg2);
                else if(provinceName.equals("宁夏"))
                    tv2=list32.get(arg2);
                else if(provinceName.equals("新疆"))
                    tv2=list33.get(arg2);
                else if(provinceName.equals("香港"))
                    tv2=list34.get(arg2);
                else if(provinceName.equals("澳门"))
                    tv2=list35.get(arg2);
                else if(provinceName.equals("台湾省"))
                tv2=list36.get(arg2);
            else
                tv2=list3.get(arg2);
            dialog.dismiss();
            hometown.setText(provinceName+"-"+tv2);
            //update the hometown
            dataEditActivity.contact.set_hometown(provinceName+"-"+tv2);;
            dataEditActivity.mBaseData.updateUser(dataEditActivity,dataEditActivity.contact);
            break;
        default:
            break;
        }
    }

    public View.OnClickListener actionClickedListener = new View.OnClickListener() {        

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            case R.id.head:
                //Fragment headportraitfragment = new PhoneRegisterDataEditHeadPortraitFragment();
                //dataEditActivity.doPushFramgentAction(headportraitfragment);
                final String[] arrayFruit = new String[] {"相册", "照相机"};
                AlertDialog.Builder builder = new AlertDialog.Builder(dataEditActivity);
                builder.setTitle("选取照片");
                builder.setSingleChoiceItems(arrayFruit, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedFruitIndex = which;
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(selectedFruitIndex==0){
                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                            intent.setType("image/*");
                            startActivityForResult(intent, GALLERY_REQUEST_CODE);
                        }
                        else{
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, CAMERA_REQUEST_CODE);
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();
                break;
            case R.id.nickname:
                Fragment nicknamefragment = new PhoneRegisterDataEditNicknameFragment();
                if (tvNickName!= null) {
                    Bundle arguments = new Bundle();
                    arguments.putString("HintText", (String) tvNickName.getText());
                    nicknamefragment.setArguments(arguments);
                }
                dataEditActivity.doPushFramgentAction(nicknamefragment);
                break;
            case R.id.age:
                final String[] age = getResources().getStringArray(R.array.age);
                AlertDialog.Builder agebuilder = new AlertDialog.Builder(dataEditActivity);
                agebuilder.setTitle("请选择年龄段");
                agebuilder.setSingleChoiceItems(age, 0, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        selectedAgeIndex = which;
                    }
                });
                agebuilder.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        TextView ageinfo = (TextView) dataEditActivity.findViewById(R.id.ageinfo);
                        if (ageinfo != null) {
                            ageinfo.setText(age[selectedAgeIndex]);
                            //update the age info
                            dataEditActivity.contact.setAge(age[selectedAgeIndex]);
                            dataEditActivity.mBaseData.updateUser(dataEditActivity,dataEditActivity.contact);
                        }
                    }
                });
                agebuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                agebuilder.create().show();
                break;
            case R.id.job:
                Fragment jobfragment = new PhoneRegisterDataEditJobFragment();
                dataEditActivity.doPushFramgentAction(jobfragment);
                break;
            case R.id.signature:
                TextView tvSignature = (TextView) view.findViewById(R.id.signatureinfo);
                Fragment signaturefragment = new PhoneRegisterDataEditSignatureFragment();
                if(tvSignature != null){
                    Bundle signaturearguments = new Bundle();
                    signaturearguments.putString("signatureHintText",(String) tvSignature.getText());
                    signaturefragment.setArguments(signaturearguments);
                }
                dataEditActivity.doPushFramgentAction(signaturefragment);
                break;
            case R.id.sex:
                Fragment sexfragment = new PhoneRegisterDataEditSexFragment();
                if(tvSex != null){
                    Bundle sexarguments = new Bundle();
                    sexarguments.putString("sexHintText",(String) tvSex.getText());
                    sexfragment.setArguments(sexarguments);
                }
                dataEditActivity.doPushFramgentAction(sexfragment);
                break;
            case R.id.hometown:
                showDialog2("点击选取省份",list1);
                break;
            }
        }
    };

    private void startImageZoom(Uri uri)
    {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    private Uri saveBitmap(Bitmap bm)
    {
        File tmpDir = new File(Environment.getExternalStorageDirectory() + "/com.samsung.sharecars.avater");
        if(!tmpDir.exists())
        {
            tmpDir.mkdir();
        }
        File img = new File(tmpDir.getAbsolutePath() + "avater.png");
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bm.compress(Bitmap.CompressFormat.PNG, 85, fos);
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Uri convertUri(Uri uri)
    {
        InputStream is = null;
        try {
            is = getActivity().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return saveBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_REQUEST_CODE)
        {
            if(data == null)
            {
                return;
            }
            else
            {
                Bundle extras = data.getExtras();
                if(extras != null)
                {
                    Bitmap bm = extras.getParcelable("data");
                    Uri uri = saveBitmap(bm);
                    startImageZoom(uri);
                }
            }
        }
        else if(requestCode == GALLERY_REQUEST_CODE)
        {
            if(data == null)
            {
                return;
            }
            Uri uri;
            uri = data.getData();
            Uri fileUri = convertUri(uri);
            startImageZoom(fileUri);
        }
        else if(requestCode == CROP_REQUEST_CODE)
        {
            if(data == null)
            {
                return;
            }
            Bundle extras = data.getExtras();
            if(extras == null){
                return;
            }
            Bitmap bm = extras.getParcelable("data");
            ImageView imageView = (ImageView)view.findViewById(R.id.headinfo);
            imageView.setImageBitmap(bm);
            //update the picture
            dataEditActivity.mBaseData.updateUserPic(dataEditActivity,dataEditActivity.contact,bm);
            //sendImage(bm);
        }
    }

//        private void sendImage(Bitmap bm)
//        {
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bm.compress(Bitmap.CompressFormat.PNG, 60, stream);
//            byte[] bytes = stream.toByteArray();
//            String img = new String(Base64.encodeToString(bytes, Base64.DEFAULT));
//    
//            AsyncHttpClient client = new AsyncHttpClient();
//            RequestParams params = new RequestParams();
//            params.add("img", img);
//            client.post("http://192.168.56.1/ImgUpload.php", params, new AsyncHttpResponseHandler() {
//                @Override
//                public void onSuccess(int i, Header[] headers, byte[] bytes) {
//                    Toast.makeText(MainActivity.this, "Upload Success!", Toast.LENGTH_LONG).show();
//    
//                }
//    
//                @Override
//                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//                    Toast.makeText(MainActivity.this, "Upload Fail!", Toast.LENGTH_LONG).show();
//                }
//            });
//        }

    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("operation", "destory");
        resultIntent.putExtras(bundle);
        dataEditActivity.backToMainActivity(resultIntent);
    }
}
