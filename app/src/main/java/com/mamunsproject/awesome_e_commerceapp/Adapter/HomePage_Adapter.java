package com.mamunsproject.awesome_e_commerceapp.Adapter;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mamunsproject.awesome_e_commerceapp.Activity.Product_Details_Activity;
import com.mamunsproject.awesome_e_commerceapp.Activity.View_All_Activity;
import com.mamunsproject.awesome_e_commerceapp.Model.HomePage_Model;
import com.mamunsproject.awesome_e_commerceapp.Model.Horizontal_Product_scrollModel;
import com.mamunsproject.awesome_e_commerceapp.Model.SliderModel;
import com.mamunsproject.awesome_e_commerceapp.Model.Wishlist_Model;
import com.mamunsproject.awesome_e_commerceapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePage_Adapter extends RecyclerView.Adapter {

    private List<HomePage_Model> homePage_modelList;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public HomePage_Adapter(List<HomePage_Model> homePage_modelList) {
        this.homePage_modelList = homePage_modelList;

        recycledViewPool = new RecyclerView.RecycledViewPool();

    }


    //Layout inflate howar age ata run hobe jate amra view type r maddome vibinno layout inflate korte pari
    @Override
    public int getItemViewType(int position) {

        switch (homePage_modelList.get(position).getType()) {

            case 0:
                return HomePage_Model.BANNER_SLIDER;

            case 1:
                return HomePage_Model.STRIP_AD_BANNER;

            case 2:
                return HomePage_Model.HORIZONTAL_PRODUCT_VIEW;

            case 3:
                return HomePage_Model.GRID_PRODUCT_VIEW;


            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case HomePage_Model.BANNER_SLIDER:
                View banner_slider_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliding_ad_layout, parent, false);
                return new Banner_SliderViewHolder(banner_slider_view);

            case HomePage_Model.STRIP_AD_BANNER:
                View strip_ad_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.strip_ad_layout, parent, false);
                return new Strip_AD_Banner_View_Holder(strip_ad_view);


            case HomePage_Model.HORIZONTAL_PRODUCT_VIEW:
                View horizontal_product_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout, parent, false);
                return new Horizontal_Product_ViewHolder(horizontal_product_view);


            case HomePage_Model.GRID_PRODUCT_VIEW:
                View grid_product_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_product_layout, parent, false);
                return new GridProductViewHolder(grid_product_view);


            default:
                return null;

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (homePage_modelList.get(position).getType()) {
            case HomePage_Model.BANNER_SLIDER:

                //====================TCODE STUDIO -19=======================
                List<SliderModel> sliderModelList = homePage_modelList.get(position).getSliderModelsList();
                ((Banner_SliderViewHolder) holder).setBannerSliderViewPager(sliderModelList);
                break;

            case HomePage_Model.STRIP_AD_BANNER:

                String resource = homePage_modelList.get(position).getResource();
                String color = homePage_modelList.get(position).getBackgroundColor();
                ((Strip_AD_Banner_View_Holder) holder).setStripAd(resource, color);
                break;


            case HomePage_Model.HORIZONTAL_PRODUCT_VIEW:

                String layout_color=homePage_modelList.get(position).getBackgroundColor();
                String horizontal_layout_title = homePage_modelList.get(position).getTitle();
               List<Wishlist_Model> viewAllProductList = homePage_modelList.get(position).getViewAllProductList();
                List<Horizontal_Product_scrollModel> horizontal_product_scrollModelList = homePage_modelList.get(position).getHorizontal_product_scrollModelList();
                ((Horizontal_Product_ViewHolder) holder).setHorizontalProduct_Layout(horizontal_product_scrollModelList, horizontal_layout_title,layout_color,viewAllProductList);

                break;

            case HomePage_Model.GRID_PRODUCT_VIEW:

                String grid_layout_color=homePage_modelList.get(position).getBackgroundColor();
                String grid_layout_title = homePage_modelList.get(position).getTitle();
                List<Horizontal_Product_scrollModel> grid_product_scrollModelList = homePage_modelList.get(position).getHorizontal_product_scrollModelList();
                ((GridProductViewHolder) holder).setGridProductLayout(grid_product_scrollModelList, grid_layout_title,grid_layout_color);

                break;

            default:
                return;

        }
    }

    @Override
    public int getItemCount() {
        return homePage_modelList.size();
    }


    public class Banner_SliderViewHolder extends RecyclerView.ViewHolder {


        private ViewPager bannerSliderViewPager;
        private int currentPage;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;
        private List<SliderModel> arrangedList;


        public Banner_SliderViewHolder(@NonNull View itemView) {
            super(itemView);

            bannerSliderViewPager = itemView.findViewById(R.id.banner_slider_viewpager);


        }

        private void setBannerSliderViewPager(List<SliderModel> sliderModelsList) {

            currentPage = 2;
            if (timer != null) {
                timer.cancel();
            }

            //========================FOR INFINITE SLIDER MODEL==================
            arrangedList = new ArrayList<>();

            for (int x = 0; x < sliderModelsList.size(); x++) {
                arrangedList.add(x, sliderModelsList.get(x));
            }
            arrangedList.add(0, sliderModelsList.get(sliderModelsList.size() - 2));
            arrangedList.add(1, sliderModelsList.get(sliderModelsList.size() - 1));

            arrangedList.add(sliderModelsList.get(0));
            arrangedList.add(sliderModelsList.get(1));
            //========================FOR INFINITE SLIDER MODEL==================

            Slider_Adapter slider_adapter = new Slider_Adapter(arrangedList);
            bannerSliderViewPager.setAdapter(slider_adapter);
            bannerSliderViewPager.setClipToPadding(false);
            bannerSliderViewPager.setPageMargin(20);


            bannerSliderViewPager.setCurrentItem(currentPage);
            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;


                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if (state == ViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(arrangedList);
                    }

                }
            };
            bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

            startBannerSlideShow(arrangedList);

            //============ User Touch korle jate animation off hoye jay abar touch chere dile jate chalu hoye jay

            bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    pageLooper(arrangedList);
                    stopBannerSlideShow();
                    // Action Up mane User Finger Soriye felche , R Down Mane Touch Korce
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        startBannerSlideShow(arrangedList);
                    }

                    return false;
                }
            });
        }

        private void pageLooper(List<SliderModel> sliderModelsList) {

            if (currentPage == sliderModelsList.size() - 2) {
                currentPage = 2;                                   //Animation todo: false
                bannerSliderViewPager.setCurrentItem(currentPage, false);
            } else if (currentPage == 1) {
                currentPage = sliderModelsList.size() - 3;        //Animation todo: false
                bannerSliderViewPager.setCurrentItem(currentPage, false);
            }
        }

        private void startBannerSlideShow(List<SliderModel> sliderModelsList) {

            Handler handler = new Handler();
            Runnable update = new Runnable() {
                @Override
                public void run() {

                    if (currentPage >= sliderModelsList.size()) {
                        currentPage = 1;
                    }
                    bannerSliderViewPager.setCurrentItem(currentPage++, true);
                }
            };
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, DELAY_TIME, PERIOD_TIME);

        }

        private void stopBannerSlideShow() {
            timer.cancel();
        }

    }


    public class Strip_AD_Banner_View_Holder extends RecyclerView.ViewHolder {
        private ImageView stripAdImage;
        private ConstraintLayout stripAdConstainer;

        public Strip_AD_Banner_View_Holder(@NonNull @NotNull View itemView) {
            super(itemView);

            stripAdImage = itemView.findViewById(R.id.strip_ad_image);
            stripAdConstainer = itemView.findViewById(R.id.strip_ad_container);

        }

        private void setStripAd(String resouce, String color) {

            Glide.with(itemView.getContext()).load(resouce).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_rounded_home)).into(stripAdImage);
            stripAdConstainer.setBackgroundColor(Color.parseColor(color));

        }

    }


    public class Horizontal_Product_ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout container;
        private TextView horizontalLayoutTitle;
        private Button horizontalviewAllButton;
        private RecyclerView horizontalRecyclerver;


        public Horizontal_Product_ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            horizontalLayoutTitle = itemView.findViewById(R.id.horizontal_scroll_layout_title);
            horizontalviewAllButton = itemView.findViewById(R.id.horizontal_scroll_layout_viewAllBUtton);
            horizontalRecyclerver = itemView.findViewById(R.id.horizontal_scroll_layout_recyclerview);
            horizontalRecyclerver.setRecycledViewPool(recycledViewPool);


        }

        private void setHorizontalProduct_Layout(List<Horizontal_Product_scrollModel> horizontal_product_scrollModelList, String title, String color, List<Wishlist_Model> viewAllProductList) {

            container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
            horizontalLayoutTitle.setText(title);

            if (horizontal_product_scrollModelList.size() > 8) {
                horizontalviewAllButton.setVisibility(View.VISIBLE);
                horizontalviewAllButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View_All_Activity.wishlist_modelsList=viewAllProductList;
                        Intent viewAllIntent = new Intent(itemView.getContext(), View_All_Activity.class);
                        viewAllIntent.putExtra("layout_code", 0);
                        viewAllIntent.putExtra("title",title);
                        itemView.getContext().startActivity(viewAllIntent);
                    }
                });
            } else {
                horizontalviewAllButton.setVisibility(View.INVISIBLE);

            }

            Horizontal_ProductScroll_Adapter horizontal_productScroll_adapter = new Horizontal_ProductScroll_Adapter(horizontal_product_scrollModelList);
            horizontalRecyclerver.setLayoutManager(new LinearLayoutManager(itemView.getContext(), RecyclerView.HORIZONTAL, false));
            horizontalRecyclerver.setAdapter(horizontal_productScroll_adapter);
            horizontal_productScroll_adapter.notifyDataSetChanged();

        }
    }


    public class GridProductViewHolder extends RecyclerView.ViewHolder {
        TextView gridLayoutTitle;
        Button gridLayoutViewAllButton;
        private GridLayout gridProductLayout;
        private ConstraintLayout container;


        public GridProductViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            gridLayoutTitle = itemView.findViewById(R.id.grid_product_layout_title);
            gridLayoutViewAllButton = itemView.findViewById(R.id.grid_product_layout_all_button);
            gridProductLayout = itemView.findViewById(R.id.grid_layout);
            container=itemView.findViewById(R.id.container);

        }

        private void setGridProductLayout(List<Horizontal_Product_scrollModel> horizontal_product_scrollModelList, String title, String color) {

            container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
            gridLayoutTitle.setText(title);

            for (int x = 0; x < 4; x++) {

                ImageView productImage = gridProductLayout.getChildAt(x).findViewById(R.id.h_s_product_image);
                TextView productTitle = gridProductLayout.getChildAt(x).findViewById(R.id.h_s_product_title);
                TextView product_description = gridProductLayout.getChildAt(x).findViewById(R.id.hs_product_description);
                TextView productPrice = gridProductLayout.getChildAt(x).findViewById(R.id.hs_product_price);

                //Adapter use korbo na tai model theke aivabe data fetch korci
                Glide.with(itemView.getContext()).load(horizontal_product_scrollModelList.get(x).getProductImage())
                        .apply(new RequestOptions().placeholder(R.drawable.ic_rounded_home)).into(productImage);
                productTitle.setText(horizontal_product_scrollModelList.get(x).getProductTitle());
                product_description.setText(horizontal_product_scrollModelList.get(x).getProductDescription());
                productPrice.setText("Tk."+horizontal_product_scrollModelList.get(x).getProductPrice()+"/-");
                gridProductLayout.getChildAt(x).setBackgroundColor(Color.WHITE);

                gridProductLayout.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent productDetailsIntent = new Intent(itemView.getContext(), Product_Details_Activity.class);
                        itemView.getContext().startActivity(productDetailsIntent);
                    }
                });

            }


            gridLayoutViewAllButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //AKAHANE INITIALZE KORCI JATE BUTTON CLICK KORAR PORE E GRID LAYOUT LOAD HOY
                    View_All_Activity.horizontal_product_scrollModelList=horizontal_product_scrollModelList;

                    Intent viewAllIntent = new Intent(itemView.getContext(), View_All_Activity.class);
                    viewAllIntent.putExtra("layout_code", 1);
                    viewAllIntent.putExtra("title",title);
                    itemView.getContext().startActivity(viewAllIntent);
                }
            });

        }
    }


}
