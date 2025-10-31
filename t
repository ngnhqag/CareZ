[33mcommit 784302f47e31372b0a63fd8f9ff418a714351a45[m[33m ([m[1;36mHEAD[m[33m -> [m[1;32mmain[m[33m, [m[1;31morigin/main[m[33m, [m[1;31morigin/HEAD[m[33m)[m
Merge: 9955502 c3314d0
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Tue Oct 21 08:51:11 2025 +0700

    Merge pull request #29 from ngnhqag/feature/setup-foods-json
    
    Feature/setup foods json

[33mcommit c3314d0181069057b6095374066f5e0044dbefc2[m[33m ([m[1;31morigin/feature/setup-foods-json[m[33m, [m[1;32mfeature/setup-foods-json[m[33m)[m
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Sun Oct 19 11:50:23 2025 +0700

    Refactor: Move hardcoded food list to JSON asset
    
    - Moves the hardcoded list of `Food` objects from `FoodRepositoryImpl` into a new `foods.json` file in the `assets` directory.
    - Creates `FoodAssetDataSource` to read and parse the `foods.json` file using Gson.
    - Injects `FoodAssetDataSource` into `FoodRepositoryImpl` and updates it to load food data from the new data source.
    - Adds `isDefault: Boolean` field to `Food`, `FoodEntity`, and `FoodFireStore` models to identify default food items.
    - Adds Gson dependency to `build.gradle.kts`.
    - Implements a click listener on the `TodayLog` button in `HomeFragment` to navigate to `TodayLogActivity`.

[33mcommit ea4524cdd5d1b8a457304552a4b8c6d19490624b[m[33m ([m[1;31morigin/feature/setup-img-for-food[m[33m, [m[1;32mfeature/setup-todaylog[m[33m, [m[1;32mfeature/setup-img-for-food[m[33m)[m
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Sat Oct 18 20:26:07 2025 +0700

    Refactor: Improve FAB menu functionality and UI
    
    This commit refactors the Floating Action Button (FAB) menu in `MainActivity`.
    
    Key changes include:
    *   Replaced individual FABs with `ExtendedFloatingActionButton`s within a `LinearLayout` for better organization.
    *   Implemented a background overlay with a blur effect (on Android 12+ and a semi-transparent fallback for older versions) when the FAB menu is open.
    *   Improved open/close animations (`from_bottom_anim`, `to_bottom_anim`) for a smoother user experience.
    *   Added logic to automatically close the FAB menu when interacting with the background overlay or the bottom navigation.
    *   The FABs now extend to show text and shrink back to icons.

[33mcommit 435ff9a146e57d4c3732815a43abd4f8dfa57faa[m
Author: ngnhqag <nhatquanglgbg@gmail.com>
Date:   Sat Oct 18 17:54:49 2025 +0700

    add navigation toggle for floating action buttons and update main activity layout for better UI control

[33mcommit 7fd62eb414080c240ec84a7898dc1ddd31749a2b[m
Author: ngnhqag <nhatquanglgbg@gmail.com>
Date:   Fri Oct 17 18:06:32 2025 +0700

    add navigation to food detail on menu item click and update resource references for consistency

[33mcommit 0dd6392d65972b99014410bb691adc43889b5573[m
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Fri Oct 17 11:35:07 2025 +0700

    add img

[33mcommit cd399a4137c184fd75aa97691a9244b905878e8a[m
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Thu Oct 16 20:35:21 2025 +0700

    test

[33mcommit 4c93dbd084bb49bc2de709edbcde568838e5a7d5[m
Merge: 7c69427 c00d73a
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Thu Oct 16 18:07:36 2025 +0700

    Merge pull request #24 from ngnhqag/feature/setup-data
    
    setup data, connect tablayout with data defaul of food fix adapter rv

[33mcommit c00d73a8089a5543f404be1a36e8958d05e81940[m[33m ([m[1;31morigin/feature/setup-data[m[33m, [m[1;32mfeature/setup-data[m[33m)[m
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Thu Oct 16 18:03:44 2025 +0700

    setup data, tablayout

[33mcommit 7c69427ed64e25fc3f3cc114b0bd963c2e8338ae[m
Merge: fd6eb39 16d1f5f
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Thu Oct 16 13:25:39 2025 +0700

    Merge pull request #23 from ngnhqag/feature/setup-xml
    
    edit food, food detail xml

[33mcommit 16d1f5f6e8a8de7b8a667f3f046efebf3645ba47[m[33m ([m[1;31morigin/feature/setup-xml[m[33m, [m[1;32mfeature/setup-xml[m[33m)[m
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Thu Oct 16 13:22:41 2025 +0700

    edit food, food detail xml

[33mcommit fd6eb39d7b26ef91c1406a36ea40371f4d632253[m
Merge: 49c38c7 c071574
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Wed Oct 15 19:19:02 2025 +0700

    Merge pull request #22 from ngnhqag/feature/setup-xml
    
    Setup Xml ( mock data, rv, ... )

[33mcommit c0715747ad5777613ab03c51eff256bd1f950e79[m
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Wed Oct 15 19:14:21 2025 +0700

    Setup Xml ( mock data, rv, ... )

[33mcommit 49c38c7bd1041cd238b6e74789de3e16ab35bb77[m
Merge: 175abc7 2a7b8f7
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Sun Oct 5 17:42:54 2025 +0700

    Merge pull request #21 from ngnhqag/fixbug/check-user-info
    
    Fixbug/check user info

[33mcommit 2a7b8f705207e1d78b20662f6001000f0e58902f[m[33m ([m[1;31morigin/fixbug/check-user-info[m[33m, [m[1;32mfixbug/check-user-info[m[33m)[m
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Sun Oct 5 17:37:56 2025 +0700

    update user info activity path and add age calculation logic in age fragment

[33mcommit 9955502da62ae6b74b326bd657946843ec9bdb92[m
Merge: 1b04dac 4c0ff7e
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Fri Oct 3 17:18:43 2025 +0700

    Merge pull request #19 from ngnhqag/fixbug/check-user-info
    
    Fixbug/check user info

[33mcommit 4c0ff7e7179a0bd329234da7269bb1490a242357[m
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Fri Oct 3 17:14:48 2025 +0700

    fix

[33mcommit 175abc7a7c63ff969e60bfbd609a8b052f82553c[m[33m ([m[1;32mfixbux/check-user-info[m[33m)[m
Merge: e85f09a 1498fae
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Wed Oct 1 17:31:22 2025 +0700

    Merge pull request #18 from ngnhqag/fixbug/sign-in
    
    fix signin-signup

[33mcommit 1498fae9e94409a0bfcf5e1de71a41c98cb0ba0d[m[33m ([m[1;31morigin/fixbug/sign-in[m[33m, [m[1;32mfixbug/sign-in[m[33m)[m
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Wed Oct 1 17:27:36 2025 +0700

    fix signin-signup

[33mcommit e85f09a7823fdaf7e920525445088da3f7302d2c[m
Merge: ac0423e 578daf9
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Mon Sep 29 18:29:38 2025 +0700

    Merge pull request #17 from ngnhqag/fixbug/sign-in
    
    fix signup

[33mcommit 578daf934a33cd5d1b49e027515222498ab5909a[m[33m ([m[1;32mfixbug/sign-up[m[33m)[m
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Mon Sep 29 18:26:19 2025 +0700

    fix signup

[33mcommit ac0423e32d5edbcbc2d72a3727e8839033a0c892[m
Merge: f4ed619 004e8d1
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Thu Sep 25 19:23:16 2025 +0700

    Merge pull request #16 from ngnhqag/fixbug/sign-in
    
    add user insertion logic to sign-in flow and update dependencies for …

[33mcommit 004e8d17ebffb9fbad1dbce3d276d2aa8a6866d8[m
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Thu Sep 25 19:20:58 2025 +0700

    add user insertion logic to sign-in flow and update dependencies for room and firestore integration

[33mcommit f4ed619a32305f70579265b264c5c07a7c43f73d[m
Merge: 0a3ef6a 524c36e
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Sun Sep 21 23:47:25 2025 +0700

    Merge pull request #14 from ngnhqag/feature/basefirestore-room
    
    add user insertion logic to sign-in flow and update dependencies for …

[33mcommit 524c36e8fa346ada3d3ad64145ae492221102769[m[33m ([m[1;31morigin/feature/basefirestore-room[m[33m, [m[1;32mfeature/basefirestore-room[m[33m)[m
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Sun Sep 21 23:37:45 2025 +0700

    add user insertion logic to sign-in flow and update dependencies for room and firestore integration

[33mcommit 0a3ef6a108ad644e691022a634be8c87fc26e0cf[m
Merge: 35d3001 5074235
Author: Nhật Quang <nhatquanglgbg@gmail.com>
Date:   Sun Sep 21 21:23:00 2025 +0700

    Merge pull request #13 from ngnhqag/feature/splash-activity
    
    add splash screen activity and view model, up