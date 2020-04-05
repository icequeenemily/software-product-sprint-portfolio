// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import com.google.sps.data.Task;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
//@WebServlet("/data")
@WebServlet("/data")
public class DataServlet extends HttpServlet {

private List<String> quotes;

   public void init() {
    quotes = new ArrayList<>();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //Loading Entities
    Query query = new Query("Task").addSort("timestamp", SortDirection.DESCENDING);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    
    PreparedQuery results = datastore.prepare(query);
    List<Task> tasks = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
      long id = entity.getKey().getId();
      String title = (String) entity.getProperty("title");
      long timestamp = (long) entity.getProperty("timestamp");

      Task task = new Task(id, title, timestamp);
      tasks.add(task);
    }
    Gson gson = new Gson();
    // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(tasks)); //said json
  }
    /**
   * Converts a ServerStats instance into a JSON string using manual String concatentation.
   */
  private String convertToJson(List<String> comments) {
    //Convert ArrayList to a Json string with numerical keys
    String json = "{";
    for (int i=0; i<comments.size(); i++) {
        json +=  "\"" + String.valueOf(i+1) +  "\"";
        json += ": ";
        json +=  "\"" + comments.get(i) + "\"";
        if (i != (comments.size()-1)) {
        json += ",";
        }
    }
    json += "}" ;
    return json;
  }

//Do post for POST 
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String text = getParameter(request, "text-input", "");

    String[] words = text.split("\\s*,\\s*");
    // Respond with the result.
    response.setContentType("text/html;");
    response.getWriter().println(Arrays.toString(words));

    //Datastore
    String title = request.getParameter("title");
    long timestamp = System.currentTimeMillis();

    Entity taskEntity = new Entity("Task");
    taskEntity.setProperty("title", text);
    taskEntity.setProperty("timestamp", timestamp);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(taskEntity);

    //Redirect
    response.sendRedirect("/index.html");

  }

  /**
   * @return the request parameter, or the default value if the parameter
   *         was not specified by the client
   */
  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}
