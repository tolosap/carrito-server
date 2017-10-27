/*
 * Copyright (c) 2017 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * carrito-server: Helps you to develop easily AJAX web applications 
 *               by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/carrito-server
 * 
 * carrito-server is distributed under the MIT License (MIT)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package helper;

import bean.ReplyBean;
import service.TipousuarioService;
import service.UsuarioService;
import javax.servlet.http.HttpServletRequest;
import service.CarritoService;
import service.LineadepedidoService;
import service.PedidoService;
import service.ProductoService;

public class MappingHelper {
    
    public static ReplyBean executeMethodService(HttpServletRequest oRequest) throws Exception {
        String ob = oRequest.getParameter("ob");
        String op = oRequest.getParameter("op");
        ReplyBean oReplyBean = null;
        Boolean oBool = null;
        switch (ob) {
            case "usuario":
                UsuarioService oUsuarioService = new UsuarioService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oUsuarioService.get();
                        break;
                    case "set":
                        oReplyBean = oUsuarioService.set();
                        break;
                    case "remove":
                        oReplyBean = oUsuarioService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oUsuarioService.getpage();
                        break;
                    case "getcount":
                        oReplyBean = oUsuarioService.getcount();
                        break;
                    case "login":
                        oReplyBean = oUsuarioService.login();
                        break;
                    case "logout":
                        oReplyBean = oUsuarioService.logout();
                        break;
                    case "check":
                        oReplyBean = oUsuarioService.check();
                        break;
                    case "getcountxtipousuario":
                        oReplyBean = oUsuarioService.getcountxtiposuario();
                        break;
                    case "getpagextipousuario":
                        oReplyBean = oUsuarioService.getpagextipousuario();
                        break;                                                                                                
                    default:
                        oReplyBean = new ReplyBean(500, "Operation not found : Please contact your administrator");
                        break;
                }
                break;
            case "tipousuario":
                TipousuarioService oTipousuarioService = new TipousuarioService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oTipousuarioService.get();
                        break;
                    case "set":
                        oReplyBean = oTipousuarioService.set();
                        break;
                    case "remove":
                        oReplyBean = oTipousuarioService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oTipousuarioService.getpage();
                        break;
                    case "getcount":
                        oReplyBean = oTipousuarioService.getcount();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation not found : Please contact your administrator");
                        break;
                }
                break;
            case "pedido":
                PedidoService oPedidoService = new PedidoService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oPedidoService.get();
                        break;
                    case "set":
                        oReplyBean = oPedidoService.set();
                        break;
                    case "remove":
                        oReplyBean = oPedidoService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oPedidoService.getpage();
                        break;
                    case "getcount":
                        oReplyBean = oPedidoService.getcount();
                        break;
                    case "getcountxusuario":
                        oReplyBean = oPedidoService.getcountxusuario();
                        break;
                    case "getpagexusuario":
                        oReplyBean = oPedidoService.getpagexusuario();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation not found : Please contact your administrator");
                        break;
                }
                break;
            case "producto":
                ProductoService oProductoService = new ProductoService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oProductoService.get();
                        break;
                    case "set":
                        oReplyBean = oProductoService.set();
                        break;
                    case "remove":
                        oReplyBean = oProductoService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oProductoService.getpage();
                        break;
                    case "getcount":
                        oReplyBean = oProductoService.getcount();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation not found : Please contact your administrator");
                        break;
                }
                break;
            case "lineaped":
                LineadepedidoService oLineadepedidoService = new LineadepedidoService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oLineadepedidoService.get();
                        break;
                    case "set":
                        oReplyBean = oLineadepedidoService.set();
                        break;
                    case "remove":
                        oReplyBean = oLineadepedidoService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oLineadepedidoService.getpage();
                        break;
                    case "getcount":
                        oReplyBean = oLineadepedidoService.getcount();
                        break;
                    case "getcountxprod":
                        oReplyBean = oLineadepedidoService.getcountxproducto();
                        break;
                    case "getpagexprod":
                        oReplyBean = oLineadepedidoService.getpagexproducto();
                        break;
                    case "getcountxpedido":
                        oReplyBean = oLineadepedidoService.getcountxpedido();
                        break;
                    case "getpagexpedido":
                        oReplyBean = oLineadepedidoService.getpagexpedido();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation not found : Please contact your administrator");
                        break;
                }
                break;
            case "carrito":
                CarritoService oCarritoService = new CarritoService(oRequest);
                switch (op) {
                    case "add":
                        oReplyBean = oCarritoService.add();
                        break;
                    case "list":
                        oReplyBean = oCarritoService.list();
                        break;
                    case "remove":
                        oReplyBean = oCarritoService.remove();
                        break;
                    case "empty":
                        oReplyBean = oCarritoService.empty();
                        break;
                    case "buy":
                        oReplyBean = oCarritoService.buy();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation not found : Please contact your administrator");
                        break;
                }
                break;
            /*default:
                oReplyBean = new ReplyBean(500, "Object not found : Please contact your administrator");
                break;*/
        }
        return oReplyBean;
    }
}
