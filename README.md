# StackLayoutManager [中文说明](README_CN.md)
--------

Android library that provides A RecyclerView.LayoutManager implementation which provides functionality to show a group of stack view.

![IMG](gif/sample1.gif)
![IMG](gif/sample2.gif)

Overview
--------

**StackLayoutManager** provides the following advantages:

* **High performance**: the bottom layer use the recyclerView recycle mechanism to allow you to display a large number of itemviews without OOM.
* **Flexibility**: you can choose which direction the card will slide out, and there are four directions to choose.
* **Hight customization**: if you want to implement animation and layout yourself, you can inherit *StackAnimation* or *StackLayout* to achieve the desired effect.
* **Easy to use**: you can start using it right away. All you need to do
is to drop the JAR file into your project and replace *LinearLayoutManager* object in your code by *com.littlemango.stacklayoutmanager.StackLayoutManager*

Gradle integration
------------------

If you're using Gradle, you can declare this library as a dependency:

```groovy
dependencies {
    implementation 'com.littlemango:stacklayoutmanager:1.0.5'
}
```

Basic usage
-----------

The simplest way to use **StackLayoutManager** is by dropping the library JAR file into your project
creating the *StackLayoutManager* object and pass it back to the RecyclerView object:

```java
StackLayoutManager manager = new StackLayoutManager();

recyclerView.setLayoutManager(manager);
```

Advanced usage
--------------

1. You can set the orientation of the card to slide out of the screen:

```java
//orientation can be one of ScrollOrientation.BOTTOM_TO_TOP or TOP_TO_BOTTOM or RIGHT_TO_LEFT or LEFT_TO_RIGHT
ScrollOrientation orientation = ScrollOrientation.BOTTOM_TO_TOP

StackLayoutManager manager = new StackLayoutManager(orientation);

recyclerView.setLayoutManager(manager);
```

2. You can set the visible item count:
```java
//in the construction method
StackLayoutManager manager = new StackLayoutManager(ScrollOrientation.BOTTOM_TO_TOP, 3);

//or in setter method
manager.setVisibleItemCount(3);
```

3. You can change the offset between items:

```java
manager.setItemOffset(50);
```

4. You can scroll smoothly or immediately to the specified position:
```java
//scroll to position with animation
recyclerView.smoothScrollToPosition(3);

//scroll to position immediately without animation
recyclerView.scrollToPosition(3);
```
5. You can set the view to turn only one page at a time, like a ViewPager, or you can turn pages continuously：
```java
manager.setPagerMode(true or false);
```

6. When in PagerView paging mode, you can set the minimum sliding velocity that triggers the paging effect:
```java
manager.setPagerFlingVelocity(3000);
```

7. You can add a item changed Listener to StackLayoutManager:
```
mStackLayoutManager.setItemChangedListener(new StackLayoutManager.ItemChangedListener() {
    @Override
    public void onItemChanged(int position) {
        mToast.setText("first visible item position is " + position);
        mToast.show();
    }
});
```

8. I use the DefaultAnimation class to provide animation, which is inherited from StackAnimation, and you can inherit both classes to achieve the desired animation effect:
```java
DefaultAnimation animation = new DefaultAnimation(ScrollOrientation.BOTTOM_TO_TOP, visibleCount);
manager.setAnimation(animation);
```
9. I use the DefaultLayout class to implement the layout of items, which inherits from StackLayout. You can inherit both classes to achieve the layout effect you want：
```java
StackLayoutManager manager = new StackLayoutManager(ScrollOrientation.BOTTOM_TO_TOP, 
                visibleCount,
                DefaultAnimation.class,
                DefaultLayout.class);
```

Sample
-------
You can clone or download this project to your computer and install **sample** apk on your phone to see the effect.

Or you can scan QR code to install the apk:

![IMG](sample.png)

License
-------
MIT License
See [MIT License](LICENSE)

