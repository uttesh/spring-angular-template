'use strict';

// modules
var gulp = require('gulp'),
        clean = require('gulp-clean'),
        concat = require('gulp-concat'),
        sass = require('gulp-sass'),
        autoprefixer = require('gulp-autoprefixer'),
        rename = require('gulp-rename');

var ng_folder = 'src/main/ng';
var static_folder = 'src/main/webapp/static';

var path = {
    html: {
        main: ng_folder + '/index.html',
        partials: [ng_folder + '/**/*.html', '!' + ng_folder + '/index.html', '!' + ng_folder + '/lib/**/*'],
        all: [ng_folder + '/**/*.html', '!' + ng_folder + '/lib/**/*']
    },
    scss: {
        main: ng_folder + '/app.scss',
        all: [ng_folder + '/**/*.scss', '!' + ng_folder + '/lib/**/*'],
        dist: ng_folder + '/dist/css/**/*.css'
    },
    js: {
        main: ng_folder + '/app.js',
        all: [ng_folder + '/**/*.js', '!' + ng_folder + '/dist/**/*', '!' + ng_folder + '/lib/**/*'],
        dist: ng_folder + '/dist/js/*.js'
    },
    bower: {
        all: [ng_folder + '/lib/**/*']
    },
    ng_js: {
        all: [ng_folder + '/lib/angular/angular.js',
            ng_folder + '/lib/angular-route/angular-route.js',
            ng_folder + '/lib/angular-animate/angular-animate.js',
            ng_folder + '/lib/angular-aria/angular-aria.js',
            ng_folder + '/lib/angular-cookies/angular-cookies.js',
            ng_folder + '/lib/angular-resource/angular-resource.js',
            ng_folder + '/lib/angular-ui-router/release/angular-ui-router.js',
            ng_folder + '/lib/angular-ui-utils/ui-utils.js',
            ng_folder + '/lib/q/q.js',
            ng_folder + '/lib/restangular/dist/restangular.js',
            ng_folder + '/lib/underscore/underscore.js',
            ng_folder + '/lib/hammerjs/hammer.js',
            ng_folder + '/lib/angular-material/angular-material.js',
            ng_folder + '/lib/angular-jwt/dist/angular-jwt.js']
    },
    extra_js: {
        all: [ng_folder + '/lib/jquery/dist/jquery.js',
            ng_folder + '/lib/jqueryui/ui/jquery-ui.js']
    }
};

// fired when just typing gulp
gulp.task('default', ['angular-bundle','extra-bundle','app-bundle', 'styles','watch']);
//gulp.task('default', ['clean-all','scripts', 'styles','deploy','watch']);

// watchers
gulp.task('watch', function() {
    // scripts
    gulp.watch([path.js.all], ['app-bundle']);
    gulp.watch([path.scss.all], ['styles']);
    gulp.watch([path.html.partials], ['deploy']);
    // styles
    gulp.watch([path.js.dist], ['deploy']);
    gulp.watch([path.scss.dist], ['deploy']);
   
});

// broswer-sync goodness
gulp.task('bs', function() {
    var paths = [
        path.html.all,
        path.scss.dist,
        path.js.dist
    ];

    bs.init(paths, {
        server: {
            baseDir: ng_folder + '/'
        }
    });
});

gulp.task('clean-dist', function() {
    return gulp.src(ng_folder + '/dist/**/*.*', {read: false}).pipe(clean());
});

gulp.task('clean-statis', function() {
    return gulp.src(static_folder + '/**/*.*', {read: false}).pipe(clean());
});

gulp.task('angular-bundle', function() {
    gulp.src(path.ng_js.all)
            .pipe(concat('angular-bundle.js'))
            .pipe(gulp.dest(ng_folder + '/dist/js'));
});

gulp.task('extra-bundle', function() {
    gulp.src(path.extra_js.all)
            .pipe(concat('extra-bundle.js'))
            .pipe(gulp.dest(ng_folder + '/dist/js'));
});

gulp.task('app-bundle', function() {
    gulp.src(path.js.all)
            .pipe(concat('app-bundle.js'))
            .pipe(gulp.dest(ng_folder + '/dist/js'));
 });
 
// Styles task
gulp.task('styles', function() {
    gulp.src(path.scss.all)
            .pipe(sass({
                onError: function(e) {
                    console.log(e);
                }
            }))
            .pipe(rename('bundle.css'))
            .pipe(autoprefixer('last 2 versions', '> 1%', 'ie 8'))
            .pipe(gulp.dest(ng_folder + '/dist/css'));
});

gulp.task('deploy', function() {
    console.log('deploy');
    gulp.src(path.html.partials).pipe(gulp.dest(static_folder + "/template"));
    gulp.src(path.js.dist).pipe(gulp.dest(static_folder + '/js'));
    gulp.src(path.scss.dist).pipe(gulp.dest(static_folder + '/css'));
});

