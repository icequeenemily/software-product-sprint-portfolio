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

import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

    private List<String> quotes;

   public void init() {
    quotes = new ArrayList<>();
    quotes.add("Here is one message!");
    quotes.add("Here is a second message!");
    quotes.add("Here is a third message!");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    String json = convertToJson(quotes);

    // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(json);
    //response.getWriter().println("<h1>Hello Emily!</h1>");
  }

    /**
   * Converts a ServerStats instance into a JSON string using manual String concatentation.
   */
   //have to use a for loop, convert ArrayList to json adding keys
  private String convertToJson(List<String> listt) {
    String json = "{";
    for (int i=0; i<listt.size(); i++) {
        //message 1
        json +=  "\"" + String.valueOf(i+1) +  "\"";
        json += ": ";
        json +=  "\"" + listt.get(i) + "\"";
        if (i != (listt.size()-1)) {
        json += ",";
        }
    }
    json += "}" ;
    return json;
  }
}
