## 前后端分离数据传输安全



安全方案：

> 前端在传参数到后端的时候，将参数转换成json格式的字符串，然后使用AES进行加密，传递到后端，
后端统一使用string类型接受参数，接收到参数后对其进行解密，得到json字符串，将其转成需要的对象
业务逻辑处理完毕以后，将结果再次进行加密，返回给前端，前端接受到后端的响应数据后对其进行解密，并转换成json对象



#### 注意

前后端加密使用的密码和偏移值都必须一直（16位字符串)

```javascript

        /**
         * 加密
         * @param data
         * @returns {string}
         */
        function encrypt(data) {
            var key  = CryptoJS.enc.Latin1.parse('niezhiliang1995a');
            var iv   = CryptoJS.enc.Latin1.parse('niezhiliang1995a');
            return CryptoJS.AES.encrypt(data, key, {iv:iv,mode:CryptoJS.mode.CBC,padding:CryptoJS.pad.ZeroPadding}).toString();
        }

        /**
         * 解密
         * @param data
         * @returns {string}
         */
        function desEncrypt(data) {
            var key  = CryptoJS.enc.Latin1.parse('niezhiliang1995a');
            var iv   = CryptoJS.enc.Latin1.parse('niezhiliang1995a');
            // 解密
            var decrypted = CryptoJS.AES.decrypt(data,key,{iv:iv,padding:CryptoJS.pad.ZeroPadding});

            return decrypted.toString(CryptoJS.enc.Utf8);
        }

```