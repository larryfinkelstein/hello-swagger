Play Scala application with Swagger
===================================

To create this application, rin activator new hello-activator play-scala

1. Update build.sbt anda add the following libraryDependeicies

    ```
    libraryDependencies ++= Seq(
      "io.swagger" %% "swagger-play2" % "1.5.1",
      "org.webjars" % "jquery-ui" % "1.11.4",
      "org.webjars" % "swagger-ui" % "2.1.4"
    }
    ```

2. Add the following entries to the conf/routes

    ```
    GET     /api-docs.json           controllers.ApiHelpController.getResources
    GET     /swagger                 controllers.Application.swagger
    ```

3. Add the swagger congrolloer to Application (or some other controller)

    ```
      def swagger = Action {
        request =>
          Ok(views.html.swagger())
      }
    ```
4. Add other Swagger annotations to your controllers

    For example: 
    
        @Api(value = "/", description = "Operations about pets")
        @ApiOperation(value = "List Cats", ...
     
5. Create the app/views/swagger.scala.html file.  You can use the file in this application, or you can 
copy the target/web/web-modules/main/webjars/lib/swagger-ui/index.html to /app/views/swagger.scala.html and update
the static references to the location in the webjar.

    For example, change:
    
    ```javascript
        <script src='lib/jquery-1.8.0.min.js' type='text/javascript'></script>
    ```
    
    to
    
    ```javascript
        <script src="@routes.Assets.versioned("/lib/swagger-ui/lib/jquery-1.8.0.min.js")" type='text/javascript'></script>
    ```

    You will also need to add the following import to the top of the swagger.scala.html file

    ```
    @import play.api.Play.current
    ```
    
    And finally, you will need to update the javascript to use some Play Template code:
    
    ```javascript
        $(function () {
          var url = window.location.search.match(/url=([^&]+)/);
          if (url && url.length > 1) {
            url = decodeURIComponent(url[1]);
          } else {
            url = "@{s"${current.configuration.getString("swagger.api.basepath")
                .getOrElse("http://localhost:9000")}/api-docs.json"}"
          }
            ...
        }
    ```

6. Add an action which will be responsible for the Swagger UI display;

    ```
    class Application extends Controller {
    
          @ApiOperation(value = "swagger", hidden=true)
          def swagger = Action {
            request =>
              Ok(views.html.swagger())
          }

    }
    ```
    
That should be all that's required.
