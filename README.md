#CircleAnimation
===========

Animation for `Circular disappearance`?

Such as `add product to cart` or `move Object to trash`.

---
Screenshot


Please waiting for loading the gif...

![](/gif/circle_animation.gif)


![](/gif/circle_animation2.gif)

##How to use
---

Run  it in one line:
    
    new CircleAnimationUtil().attachActivity(MainActiviy.this).setTargetView(mTargetView).setDestView(mDestView).startAnimation();

	
Gradle

	dependencies {
		compile( 'com.dk.animation.circle:library:0.1.0@aar')
	}
	
---

##Other Settings

TODO desc

--
##Note:

- Support Android 4.0.3+.

- If you want to run it under 2.x versions, just replace ObjectAnimator with `nineoldandroids`.

- For better performance, using it with `RecyclerView` may be a good idea.

- The build_for_jcenter_publish.gradle is for publishing library on Jcenter, do not run that.

---
##Thanks to

[CircleImageView](https://github.com/hdodenhof/CircleImageView)

---


#License

    Copyright 2015 Antonio Leiva

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

---
Developed By


Dean <93440331@qq.com>  

Weibo：http://weibo.com/u/2699012760

![](https://avatars0.githubusercontent.com/u/5019523?v=3&s=460)
