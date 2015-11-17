module.exports = function(grunt) {

   var path = require('path');

    // 配置
    grunt.initConfig({
        pkg : grunt.file.readJSON('package.json'),
        clean: {
            options: {
              force: true
            },
            all: ['dist/']
        },
        uglify: {
          options: {
            banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
          },
          build: {
            files: {
                 'dest/bundle.min.js': ['dest/bundle.js','fakeData.js'],
                 'dest/wb-apply-bundle.min.js': ['dest/wb-apply-bundle.js']
             }
           }
      },
      connect: {
           server: {
             options: {
               port: 8010,
               keepalive: true,
               base: ''
             }
           }
      },
      webpack: {

          abc:{
              cache: true,
              entry: './src/jsx/index.jsx',
              output: {
                filename: 'dest/bundle.js'
              },
              module: {
                loaders: [
                  {test: /\.jsx/, loader: 'jsx-loader'}
                ]
              },
              resolve: {
                alias: {
                  'fakeData.js': path.join(__dirname, 'src/fakeData.js')
                }
              },
              externals: {
                'react': 'React',
                'react/addons': 'React'
              },
              watch: true, // use webpacks watcher
              // You need to keep the grunt process alive

              keepalive: true, // don't finish the grunt task
              // Use this in combination with the watch option

              inline: true  // embed the webpack-dev-server runtime into the bundle
              // Defaults to false

            },
        ts:{
            entry: './src/ts/index.ts',
            output: {
              filename: 'dest/ts_bundle.js'
            },
            resolve: {
              // Add `.ts` and `.tsx` as a resolvable extension.
              extensions: ['', '.webpack.js', '.web.js', '.ts', '.tsx', '.js']
            },
            module: {
              loaders: [
                // all files with a `.ts` or `.tsx` extension will be handled by `ts-loader`
                { test: /\.tsx?$/, loader: 'ts-loader' }
              ]
            },
            watch: true,
            keepalive: true,
            inline: true
          }
        },
        concurrent: {
            target4: {
                tasks: ['webpack:ts','connect'],
                options: {
                  logConcurrentOutput: true
                }
            }
          }
   });

      // Load the plugin that provides the "uglify" task.

      grunt.loadNpmTasks('grunt-contrib-clean');

      grunt.loadNpmTasks('grunt-contrib-uglify');

      grunt.loadNpmTasks('grunt-contrib-connect');

      grunt.loadNpmTasks('grunt-webpack');

      grunt.loadNpmTasks('grunt-concurrent');

      grunt.registerTask('compile', ['webpack','uglify']);

      grunt.registerTask('compress', ['webpack:ts','uglify']);

      // Default task(s).
      grunt.registerTask('default', ['clean','concurrent:target4']);

  };
