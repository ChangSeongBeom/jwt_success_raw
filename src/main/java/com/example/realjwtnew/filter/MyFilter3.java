//package com.example.realjwtnew.filter;
//
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class MyFilter3 implements Filter {
//
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        HttpServletRequest req=(HttpServletRequest) request;
//        HttpServletResponse res=(HttpServletResponse) response;
//
//
////        String headerAuth=req.getHeader("Authorization");
// //       System.out.priantln(headerAuth);
//  //      System.out.println("필터3");
//
////
////        if (headerAuth.equals("cos")){
////            chain.doFilter(request,response);
////        }else{
////            PrintWriter out=res.getWriter();
////            out.print("인증안댐");
////        }
//    }
//}
