package com.example.group_14_project.model;


import android.graphics.Bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.widget.ImageView;

import java.io.File;
import java.util.Collections;
import java.util.List;
import android.graphics.Bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.example.group_14_project.R;

import java.util.ArrayList;

// Deck: Holds two sets of 7 images (preset images of planets and animals) and assigns either
// set to a deck of cards
public class Deck {

    private static final int DRAW_PILE_ALL_ORDER_TWO = 7;
    private static final int DRAW_PILE_ALL_ORDER_THREE = 13;
    private static final int DRAW_PILE_ALL_ORDER_FIVE = 31;

    public int numOfCards = 5;
    public int numImgPerCard = 3;
    public int maxCards = 7;

    private Option options = Option.getInstance();
    private List<Card> myDeck = null;

    Image imgAnim1 = new Image(R.drawable.anim1);
    Image imgAnim2 = new Image(R.drawable.anim2);
    Image imgAnim3 = new Image(R.drawable.anim3);
    Image imgAnim4 = new Image(R.drawable.anim4);
    Image imgAnim5 = new Image(R.drawable.anim5);
    Image imgAnim6 = new Image(R.drawable.anim6);
    Image imgAnim7 = new Image(R.drawable.anim7);
    Image imgAnim8 = new Image(R.drawable.anim8);
    Image imgAnim9 = new Image(R.drawable.anim9);
    Image imgAnim10 = new Image(R.drawable.anim10);
    Image imgAnim11 = new Image(R.drawable.anim11);
    Image imgAnim12 = new Image(R.drawable.anim12);
    Image imgAnim13 = new Image(R.drawable.anim13);
    Image imgAnim14 = new Image(R.drawable.anim14);
    Image imgAnim15 = new Image(R.drawable.anim15);
    Image imgAnim16 = new Image(R.drawable.anim16);
    Image imgAnim17 = new Image(R.drawable.anim17);
    Image imgAnim18 = new Image(R.drawable.anim18);
    Image imgAnim19 = new Image(R.drawable.anim19);
    Image imgAnim20 = new Image(R.drawable.anim20);
    Image imgAnim21 = new Image(R.drawable.anim21);
    Image imgAnim22 = new Image(R.drawable.anim22);
    Image imgAnim23 = new Image(R.drawable.anim23);
    Image imgAnim24 = new Image(R.drawable.anim24);
    Image imgAnim25 = new Image(R.drawable.anim25);
    Image imgAnim26 = new Image(R.drawable.anim26);
    Image imgAnim27 = new Image(R.drawable.anim27);
    Image imgAnim28 = new Image(R.drawable.anim28);
    Image imgAnim29 = new Image(R.drawable.anim29);
    Image imgAnim30 = new Image(R.drawable.anim30);
    Image imgAnim31 = new Image(R.drawable.anim31);

    Image ImgAnim1_word = new Image(R.drawable.anim1_word);
    Image ImgAnim2_word = new Image(R.drawable.anim2_word);
    Image ImgAnim3_word = new Image(R.drawable.anim3_word);
    Image ImgAnim4_word = new Image(R.drawable.anim4_word);
    Image ImgAnim5_word = new Image(R.drawable.anim5_word);
    Image ImgAnim6_word = new Image(R.drawable.anim6_word);
    Image ImgAnim7_word = new Image(R.drawable.anim7_word);
    Image ImgAnim8_word = new Image(R.drawable.anim8_word);
    Image ImgAnim9_word = new Image(R.drawable.anim9_word);
    Image ImgAnim10_word = new Image(R.drawable.anim10_word);
    Image ImgAnim11_word = new Image(R.drawable.anim11_word);
    Image ImgAnim12_word = new Image(R.drawable.anim12_word);
    Image ImgAnim13_word = new Image(R.drawable.anim13_word);
    Image ImgAnim14_word = new Image(R.drawable.anim14_word);
    Image ImgAnim15_word = new Image(R.drawable.anim15_word);
    Image ImgAnim16_word = new Image(R.drawable.anim16_word);
    Image ImgAnim17_word = new Image(R.drawable.anim17_word);
    Image ImgAnim18_word = new Image(R.drawable.anim18_word);
    Image ImgAnim19_word = new Image(R.drawable.anim19_word);
    Image ImgAnim20_word = new Image(R.drawable.anim20_word);
    Image ImgAnim21_word = new Image(R.drawable.anim21_word);
    Image ImgAnim22_word = new Image(R.drawable.anim22_word);
    Image ImgAnim23_word = new Image(R.drawable.anim23_word);
    Image ImgAnim24_word = new Image(R.drawable.anim24_word);
    Image ImgAnim25_word = new Image(R.drawable.anim25_word);
    Image ImgAnim26_word = new Image(R.drawable.anim26_word);
    Image ImgAnim27_word = new Image(R.drawable.anim27_word);
    Image ImgAnim28_word = new Image(R.drawable.anim28_word);
    Image ImgAnim29_word = new Image(R.drawable.anim29_word);
    Image ImgAnim30_word = new Image(R.drawable.anim30_word);
    Image ImgAnim31_word = new Image(R.drawable.anim31_word);

    Image imgPlan1 = new Image(R.drawable.fruit1);
    Image imgPlan2 = new Image(R.drawable.fruit2);
    Image imgPlan3 = new Image(R.drawable.fruit3);
    Image imgPlan4 = new Image(R.drawable.fruit4);
    Image imgPlan5 = new Image(R.drawable.fruit5);
    Image imgPlan6 = new Image(R.drawable.fruit6);
    Image imgPlan7 = new Image(R.drawable.fruit7);
    Image imgPlan8 = new Image(R.drawable.fruit8);
    Image imgPlan9 = new Image(R.drawable.fruit9);
    Image imgPlan10 = new Image(R.drawable.fruit10);
    Image imgPlan11 = new Image(R.drawable.fruit11);
    Image imgPlan12 = new Image(R.drawable.fruit12);
    Image imgPlan13 = new Image(R.drawable.fruit13);
    Image imgPlan14 = new Image(R.drawable.fruit14);
    Image imgPlan15 = new Image(R.drawable.fruit15);
    Image imgPlan16 = new Image(R.drawable.fruit16);
    Image imgPlan17 = new Image(R.drawable.fruit17);
    Image imgPlan18 = new Image(R.drawable.fruit18);
    Image imgPlan19 = new Image(R.drawable.fruit19);
    Image imgPlan20 = new Image(R.drawable.fruit20);
    Image imgPlan21 = new Image(R.drawable.fruit21);
    Image imgPlan22 = new Image(R.drawable.fruit22);
    Image imgPlan23 = new Image(R.drawable.fruit23);
    Image imgPlan24 = new Image(R.drawable.fruit24);
    Image imgPlan25 = new Image(R.drawable.fruit25);
    Image imgPlan26 = new Image(R.drawable.fruit26);
    Image imgPlan27 = new Image(R.drawable.fruit27);
    Image imgPlan28 = new Image(R.drawable.fruit28);
    Image imgPlan29 = new Image(R.drawable.fruit29);
    Image imgPlan30 = new Image(R.drawable.fruit30);
    Image imgPlan31 = new Image(R.drawable.fruit31);

    Image imgPlan1_word = new Image(R.drawable.fruit1_word);
    Image imgPlan2_word = new Image(R.drawable.fruit2_word);
    Image imgPlan3_word = new Image(R.drawable.fruit3_word);
    Image imgPlan4_word = new Image(R.drawable.fruit4_word);
    Image imgPlan5_word = new Image(R.drawable.fruit5_word);
    Image imgPlan6_word = new Image(R.drawable.fruit6_word);
    Image imgPlan7_word = new Image(R.drawable.fruit7_word);
    Image imgPlan8_word = new Image(R.drawable.fruit8_word);
    Image imgPlan9_word = new Image(R.drawable.fruit9_word);
    Image imgPlan10_word = new Image(R.drawable.fruit10_word);
    Image imgPlan11_word = new Image(R.drawable.fruit11_word);
    Image imgPlan12_word = new Image(R.drawable.fruit12_word);
    Image imgPlan13_word = new Image(R.drawable.fruit13_word);
    Image imgPlan14_word = new Image(R.drawable.fruit14_word);
    Image imgPlan15_word = new Image(R.drawable.fruit15_word);
    Image imgPlan16_word = new Image(R.drawable.fruit16_word);
    Image imgPlan17_word = new Image(R.drawable.fruit17_word);
    Image imgPlan18_word = new Image(R.drawable.fruit18_word);
    Image imgPlan19_word = new Image(R.drawable.fruit19_word);
    Image imgPlan20_word = new Image(R.drawable.fruit20_word);
    Image imgPlan21_word = new Image(R.drawable.fruit21_word);
    Image imgPlan22_word = new Image(R.drawable.fruit22_word);
    Image imgPlan23_word = new Image(R.drawable.fruit23_word);
    Image imgPlan24_word = new Image(R.drawable.fruit24_word);
    Image imgPlan25_word = new Image(R.drawable.fruit25_word);
    Image imgPlan26_word = new Image(R.drawable.fruit26_word);
    Image imgPlan27_word = new Image(R.drawable.fruit27_word);
    Image imgPlan28_word = new Image(R.drawable.fruit28_word);
    Image imgPlan29_word = new Image(R.drawable.fruit29_word);
    Image imgPlan30_word = new Image(R.drawable.fruit30_word);
    Image imgPlan31_word = new Image(R.drawable.fruit31_word);

    private Image[] animImages = new Image[]{
            imgAnim1, imgAnim2, imgAnim3, imgAnim4,
            imgAnim5, imgAnim6, imgAnim7, imgAnim8,
            imgAnim9, imgAnim10, imgAnim11, imgAnim12,
            imgAnim13, imgAnim14, imgAnim15, imgAnim16,
            imgAnim17, imgAnim18, imgAnim19, imgAnim20,
            imgAnim21, imgAnim22, imgAnim23, imgAnim24,
            imgAnim25, imgAnim26, imgAnim27, imgAnim28,
            imgAnim29, imgAnim30, imgAnim31
    };

    private Image[]animImages_word = new Image[]{
            ImgAnim1_word,  ImgAnim2_word, ImgAnim3_word, ImgAnim4_word,
            ImgAnim5_word, ImgAnim6_word, ImgAnim7_word, ImgAnim8_word,
            ImgAnim9_word, ImgAnim10_word, ImgAnim11_word, ImgAnim12_word,
            ImgAnim13_word, ImgAnim14_word, ImgAnim15_word, ImgAnim16_word,
            ImgAnim17_word, ImgAnim18_word, ImgAnim19_word, ImgAnim20_word,
            ImgAnim21_word, ImgAnim22_word, ImgAnim23_word, ImgAnim24_word,
            ImgAnim25_word, ImgAnim26_word, ImgAnim27_word, ImgAnim28_word,
            ImgAnim29_word, ImgAnim30_word, ImgAnim31_word
    };

    private Image[]mergedImages;

    private Image[] flickrImages;

    private Image[] planImages = new Image[]{
            imgPlan1, imgPlan2, imgPlan3, imgPlan4, imgPlan5, imgPlan6, imgPlan7,
            imgPlan8, imgPlan9,imgPlan10,imgPlan11, imgPlan12,imgPlan13, imgPlan14,
            imgPlan15,imgPlan16,imgPlan17,imgPlan18,imgPlan19,imgPlan20,imgPlan21,
            imgPlan22,imgPlan23,imgPlan24,imgPlan25,imgPlan26,imgPlan27,imgPlan28,
            imgPlan29,imgPlan30,imgPlan31
    };

    private  Image[] planImages_word = new Image[]{
            imgPlan1_word, imgPlan2_word, imgPlan3_word, imgPlan4_word, imgPlan5_word, imgPlan6_word, imgPlan7_word,
            imgPlan8_word,imgPlan9_word,imgPlan10_word,imgPlan11_word,imgPlan12_word,imgPlan13_word,imgPlan14_word,
            imgPlan15_word,imgPlan16_word,imgPlan17_word,imgPlan18_word,imgPlan19_word,imgPlan20_word,imgPlan21_word,
            imgPlan22_word, imgPlan23_word, imgPlan24_word, imgPlan25_word, imgPlan26_word, imgPlan27_word, imgPlan28_word,
            imgPlan29_word, imgPlan30_word, imgPlan31_word

    };

    public Deck(int numOfCards, int numImgPerCard){
        this.numOfCards = numOfCards;
        this.numImgPerCard = numImgPerCard;
        myDeck = new ArrayList<>();
        maxCards = options.getMaxOrderCardNum();
        initializeDeck();
        if(options.isFlickr()){
            createFlickrImgSet(options.getMaxOrderCardNum());
        }else{
            createMergedDeck();
        }
        if(options.isAnimals()){
            if(options.getWordMode()){
                assignImgToCards(mergedImages,maxCards);
            }else{
                assignImgToCards(animImages, maxCards);
            }
        }else if(options.isFood()){
            if(options.getWordMode()){
                assignImgToCards(mergedImages, maxCards);
            }else{
                assignImgToCards(planImages, maxCards);
            }

        }else if(options.isFlickr()){

            assignImgToCards(flickrImages, maxCards);
        }
        shuffleDeck();
        shuffleCards();
    }

    private void createMergedDeck() {
        if(options.isAnimals()){
            mergedImages = mergeImageArrays(animImages,animImages_word);
        }else if(options.isFood()){
            mergedImages = mergeImageArrays(planImages,planImages_word);
        }
    }

    private void createFlickrImgSet(int maxCards) {
        flickrImages = new Image[maxCards];
        for (int i = 0; i < maxCards; i++) {
            flickrImages[i] = new Image(i);
        }
    }

    int WordOrImage(){
        Random r = new Random();
        int decision = r.nextInt(2);
        return decision;
    }

    public void assignImgToCards(Image[] images, int maxCardsForOrder) {
        int i,j,k,r=0,n=numImgPerCard-1;

        int count;

        // first card (card 1)

        for(i = 0;i <= n; ++i) { // adding n images
            if(options.getWordMode()){
                count = WordOrImage();
                if(count == 0){
                    myDeck.get(r).addImage(images[i]);
                }
                else {
                    myDeck.get(r).addImage(images[i+maxCardsForOrder]);
                }
            }else{
                myDeck.get(r).addImage(images[i]);
            }
        }

        // n following cards
        for(j = 0; j < n; ++j) {
            ++r;
            myDeck.get(r).addImage(images[0]);
            for(k = 0; k < n; ++k) {
                if(options.getWordMode()){
                    count = WordOrImage();
                    if(count == 0){
                        myDeck.get(r).addImage(images[n + 1 + n*j + k]);
                    }
                    else
                    {
                        myDeck.get(r).addImage(images[(n + 1 + n*j + k)+maxCardsForOrder]);
                    }
                }else{
                    myDeck.get(r).addImage(images[n + 1 + n*j + k]);
                }
            }
        }

        // n*n following cards
        for(i = 0; i < n; ++i) {
            for(j = 0; j < n; ++j) {
                ++r;
                myDeck.get(r).addImage(images[i+1]);
                for(k = 0; k < n; ++k) {
                    if(options.getWordMode()){
                        count = WordOrImage();
                        if(count == 0){
                            myDeck.get(r).addImage(images[n + 1 + n*k + (i*k+j)%n]);
                        }
                        else
                        {
                            myDeck.get(r).addImage(images[(n + 1 + n*k + (i*k+j)%n)+maxCardsForOrder]);
                        }
                    }else{
                        myDeck.get(r).addImage(images[n + 1 + n*k + (i*k+j)%n]);
                    }
                }
            }
        }
    }

    public Card getCardAt(int i){
        return myDeck.get(i);
    }

    public void initializeDeck(){
        for(int i = 0; i < maxCards; ++i){
            myDeck.add(new Card(numImgPerCard));
        }
    }

    public Image[] mergeImageArrays(Image[] pictures, Image[] words){
        Image[] mergedImages = new Image[maxCards*2];
        for(int i = 0; i < maxCards; ++i){
            mergedImages[i] = pictures[i];
        }
        for(int i = maxCards; i < maxCards*2;++i){
            mergedImages[i] = words[i-maxCards];
        }
        return mergedImages;
    }

    public int getMaxCards(){
        return maxCards;
    }

    public boolean isPairedImage(Image a, Image b) {
        for (int i = maxCards; i < maxCards * 2; ++i) {
            if ((a == mergedImages[i - maxCards] && b == mergedImages[i])
                    || (b == mergedImages[i - maxCards] && a == mergedImages[i])) {
                return true;
            }
        }
        return false;
    }

    public void shuffleDeck(){
        Random rand = new Random();
        for(int i = 0; i < maxCards; i++){
            int randomIndex = (i + rand.nextInt(maxCards - i)) % maxCards;
            if(randomIndex >= 0 && randomIndex < maxCards) {
                Collections.swap(myDeck,i,randomIndex);
            }
        }
    }

    private void shuffleCards() {
        for (Card i:myDeck
             ) {
            Collections.shuffle(i.getImages());

        }
    }



}
