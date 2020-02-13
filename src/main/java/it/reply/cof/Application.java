package it.reply.cof;

import it.reply.cof.client.VPOSClient;
import it.reply.cof.client.VPOSSimpleClient;
import it.reply.cof.dto.PaymentInfo;
import it.reply.cof.dto.request.*;
import it.reply.cof.utils.Utils;
import it.reply.cof.utils.exception.COFException;

public class Application {
    public static final String BASE64TEMPLATE = "PGh0bWw+Cgo8Ym9keT4KICAgIDxzdHlsZT4KICAgICAgICBib2R5IHsKICAgICAgICAgICAgYmFja2dyb3VuZC1pbWFnZTogdXJsKCdodHRwczovL2kuZ2lwaHkuY29tL21lZGlhLzNvRWpJNlNJSUhCZFJ4WEk0MC9naXBoeS53ZWJwJyk7CiAgICAgICAgICAgIGJhY2tncm91bmQtcmVwZWF0OiBuby1yZXBlYXQ7CiAgICAgICAgICAgIGJhY2tncm91bmQtYXR0YWNobWVudDogZml4ZWQ7CiAgICAgICAgICAgIGJhY2tncm91bmQtcG9zaXRpb246IGNlbnRlcjsKICAgICAgICB9CiAgICA8L3N0eWxlPgo8L2JvZHk+Cgo8L2h0bWw+";
    private static final String SHOP_ID = "129281292800109";
    private static final String PAN_ALIAS = "0000409500729966732";
    private static final String MAC_KEY_VPOS = "fU-9et-s-Sj8W---E8uhUDu9fEzqr8hH3L95s48r9nq-cq3cBXbp-tZsvGQU--t-nqmtaW-7x-7-C2PdcuFdbHuShQ-pYVWnr-4-";
    private static final String API_RESULT_KEY = "E-vmE-GHXmx73-Lfg24LztZ-7-yCyVsKn4QXphL5qzf-Kr-Cf-JWpZwZgaZRA5dR9V677xL4uCbc-Ce--8h2-tdrSu--QKjF-nZh";
    private static final String MAC_KEY_REDIRECT = "VnFBty7us-7QubjwHcjFaq4hb5-5n5-VdaymsXfqukzd3nhYhsLvuL5nFs-sCrJT--n9-J7zghhUYpFUw6q4KxCQ-XND6dV—abW";
    private static final String URL_REDIRECT = "https://atpostest.ssb.it/atpos/pagamenti/main";
    private static final String URL_DONE = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URL_BACK = "http://localhost:8080/payment-gateway/vpos/tokenize";
    private static final String URLMS = "https://te.t-frutta.eu/TImooneyWS/app_api/v10/payment/cardData?consumerId=3b350c34-d923-4552-91bf-67bc4f99da92";
    private static final String URL_WEB_API = "https://atpostest.ssb.it/atpos/apibo/apiBOXML.app";
    private static final String OPERATOR_ID = "John Doe";
    private static final String PARES = "eNqtWNmym8iyfecrHD6PhJtRCBzyPlGMAgQS8/CGAAFikhgE0tdftPf20D7uDnfH5UUoycrKqsyVK6s2/53r6sMt7fqibb58xP5AP35Im7hNiib78tGxxU/0x/++bOy8S1PeSuOxS182Wtr3UZZ+KJIvHz1D9mzP+LRjd5q2U3cadn19hgOOD7n4Sfz08WVzAGbav6pfoi7tMQwl1iS6IpdP71O/LDP/gW+Qr3+XObo4j5rhZRPFV1bWXzCcIFfUer2m6Q3yLtvUaSfzLyiK//nZIG8fNsh3M4fx+dYv7s9F8oJrxfV8B3zinMCtuYxKcmoHGw46vf2yQZ4amyQa0hccxRfrKPkBZT7j1OcVtkFe5ZvL0xyo23GxvaigG+RHyWbZqG7Zx/sLs17c/fZvk86XtkmfYzbIt/cN8t25S9Qs6/n+PJe92F6kG9t/2QxF/YNTGPoZX38miQ3yKt/0QzSM/UuwQd7fNnF0u72cFZ6qB9OdztmJYwUA2N0DcCVYnmWxryqbNC5e0KdTy+/rKFBlbVcMef1CvOl8F2yQpyvIa1RfNlaRNctkXfphSaWm//IxH4bLZwSZpumPifij7TLkuUEIyiCLQtIX2X8+vo1KE7k5tf9oGBc1bVPEUVU8omFJFC0d8jb58M23X5mxzaclDDEF7tNi6lOMkc2npwQlsNViE/m10R9W9juz/Oxs10ef+jzCnhP8ZOhlY6an9JkR6QfHlL98/M//wIIvsrQf/s28X+f80cJXe25UjenLNY2KZm/xLkUEMat6cYuE+7s1HY7Tl6/j3jQ3yDdH31fxFrIftuZNsfRiH62PwGNIGsO8O8seXU+qahrWuxrINz24SchsNvq+D4i2Vw4nIdnTCdMVpdYksbbf7pwgsm8nvmD1qUWO+n5qS/Z4dGY+CEwpzHJOv3ocQIL8RNBUQCiddU9v25OZuAdul1CMscNs7GaMXoPjohriYXYuEKuLsqgsWyPtLl6+I267o3xURBCVhlWWx6t0ma4r/GYnWWCKD/7O9VRLcYw9FJrFWL5Z33eIXY2hZMTuw2FodoYvhqt5sS3dXJjlBWMuykA5rlKMoah6TTIr8bHt4Mg8+vLM6/iFSrZqf4nzqzIMvBuexGNjwabWHWicQnpCh0sm4mt9ZtSwdujKtyPjBnvciB9Y48uXH1LnPSJqen+LgL9CGT4aohfo9ZVLu6E4LUm8FCdIk2XBtjkOPLwMTDILMtmwTfFGP0qGqPT6sb/K5UrfCWegs1l5zctCYiaUBYYjAp5jocIWdhooJYA5AptrnOtq89YGRzbTXRa0tihgeUyYVVzqeVwbmYsz96NkEpFnVppFTtAOBLxrGDvh7uqBr6Ohr/CBr5SymNziei4TvCpDi7VDT8fi2uWPuFnJQlIdC3DXrH6CVOPVAC/cFS/wZinwtMx2tMzB3XPiK5WBM08DSkzoWOjLmUO4hSzorMaSPm8LGKTx5ayfwUo7B5hWtYtQxjTemfa2cNf57LHIJo//8zIt15h5HqjPZULPdQK02psPodA48KrEzVpoOautLJqigxmZ7YmPRHLvAS7eQ46VLFfRDVvwNdaRoOcAMGv7wNOfSo+EWz0iTxhD35mybDEKUImzrpIlHwneEJ67DwAps/wEnt9VCLRL6AwOvXjeti8MNRpgTu2ODUcc7+ZZKOvIGMxQwqorekoITChtRpwkdiJvOZfT5x00JqtzH7YZjkT3W/kI5zqieRhU8KqA0blxbm0qN0jgIGh6BUxJM+f9ubqr64Lnp67yVpDuwqokMlsU4NSaXVW5N/jWvhM62CDxJiJLS2ACypeIYaiuhgQLjKmLPasdLlZaq30Deba7klVGthaA62mwJgh6WO/WcA5Y7HixnZE3JcLXZh65igEZ7tMFZqhYreQFoFut5CBN8HYHjDdXncBe8YQko3ZNDsc9c2dOI7O9KqxwOrG8nZor2b6fUJgXCGH0AxZbnYUm7qFxdepV8h6zcuRJ9KyxB5nx8YYdIu8IMo0FQDpn6bLjCTplUfsMcyJPFtueObahpAULkcXmy5/s8e8wAX0Fxb/FBPQGCmeSp3dMzPPfYcI64gz6xEKWC6C9XgKonxI9Hy2eGurILI+PMw+Up9tbUxPAGQCN1V7XrRiGpoFW4rheetYCdlq+API1lRN+ElhkMoSloIgt/4vCAfYcWD4fdG63X9MMHd+upXE5UMkBhZyjurrtx7Rtx4Mcz0qnOs3D6gknYRlhCGRmdZ4CV9sns6aYJ+kIhIQmMnhlXwcDR64UhKC6oit059yWQshMPT0eTobgI5klMWHLOWtN0GrHb3o6hTNlKwUgOQrr1umIEYuKVII0MkkNfBpx8MjY84690rmgnw7JLG+Z/Z6rp/R2i/E9y2a84/NCPa7VC3uheE1qme16y0M0PObdg9QwrGOk1QW57yhaNPXeITi5IbRoTzE7SzpgjI6oZ0AMu26VRkF4wqfaTBkhhna3KAm2XZGSWZ6T5ORQBvD9xr3xRBEqlTcJMGbmj6l/SPoyqEOupT/QU5ysGWKm0wLSF0aANsj/lPtfE0DxJABC+koAJlDVq7fTtnZwLDO4C+sylGhymngjUNQ2lPNbrC/xE1kDTFAWaDP3eMsSFgQ2qFxbM9FJektBVZjNv85hWxghjZu+Vszqa5U03qqkfySUPtwml1Byfs7dbeiRmXYWqsXAa04+K2js1mJv1uJZFjBeFvUqINx76Oi3VwM1c0s4ljdskIoTOmtngGm2MEPa2SC1hxItwrtuP4WLqXeZJkuKZvQT98YykjAprvMQEo3t37zONQf6G7fn3Rn0b3vT24rzN8D/2V0Td0eZl2f1AS5vBjRbrsL8KIR5cGd14/69AEBvFaB6xIRbxQVrJ56cGajwW+zxJA9oYQ/c4WU4HVJiljSwzY+OXadFsD9kF2Qduqqw8F3thRg4SoMcbHtYXC9t4Si6lyssNVA4aylOtsF1OKllNrbbMowf7Vm4kFHVGsebHYY+Nfm6KCfn1FldL8Sksk17PJsysNtBgUQatSJTPGCDfEDFm7emwhhFqFGG69VxkGDs4OUCKS8bpljMgGVwHYuyh88YVl9aIUSgi9YHfPHAnSumOTpBStVs6KJqStG+KHhhJSsdJqlXJLM1bOil7VxSODlapy677Qb8BiCarPzMv/oUmZQRXalizZwPnkGBQ0Tr62tlsezIh3jiVXg9yPW8llwd3yKCxLky6pYGlMQ9R11FUvWbhzJG5Z0DhVP62+/sQU9ZugDpiQ0TtRe0TWz2LJYL8Hiwh15LrUGz4EQvcVp6CSOYtsar8lJvAkFUmOCC7Zx1M25ND1HvRUOv1Vq8T+txydTLmwFLE7JoiX1I5VlQl1kQTL+LUuivYPq7KIX+Cqa/i1LoG0wD2VCoCyfyjBtw4SxVQwHvoxqz/5pRNKPToG2ZPdZ9nvfxflVj6qGlDkZVnU/HByln5xD3QJKcvIOwIO4ymofivKqIIjklqN77homW0FXKGp90dumZP4jofMn3tOyDCsm7S1bdT07C9Mmc7TK8BqaywyrlUCSUgrndDhajPhCgiwdT4XBUSOQsV+NaIlj9rq8aGKWS8/pWpXW6LkHyONOZmylz2JMUj3FDLMalKqbGsIW6sB29Y75/sH4UX7XqujMyMryWrW7IeoC2Hg2rnGr4DYpZJNNGuxvTWnp5EmuUXq1XNLTO2fvK9xgxPuE0ae4nTgFrXh4KKmRjNGKSbbXCtgUtGn5n94VAicOqWJUqY2Ke/LAUyOr3TiOVQvm4TWqNFY/sHzAKj/ELo3T3b0eK347j80jxb9un/6cjhTNB/7R9ej9K3JdTw7wwyh3SeYBq7rejxKvwXfZbUIT+jjF/B4rQ3zHm70AR+jNjLuG05J8bAHZpAPiFQQ7gGW6j5ZZ3FhxYN6J3Wyi+TMBNk1ZmHuF5CqnCoagQ5jK5YOcEP3jYaKdF6SZ58vDT1oiOspFdanBXMHmSj3MC7Qs/O11cHw7WpXqrRtbb5n5es/a6FzVG1LdaEvm0GADc5+8wxYYCM1NY2h3ZoPMm/QidJjvEM1peXY0SgMAsdzoTT1fFYcgHeiGSyWHj1CjMAo6twZELK/fcU+s1zTbuE74moIy6HcaTBg/3noZFY9TwJmgYVymwUr0TPp9YhaOEx/RG8rBnjG5lYJ0ruvNeSsuFHl2oVB+0qG0v1OlxLtIeP599U41WmZ9gE3yaTZV3FDZHzo04J35xZ1Osw7DxAlrvIAqeTkJIvFcelLUzrnV5xxZCmAQAIp1buvFfs4gN9j+SCPRLFpGXZPK9FXypdXa/yh0du9+DsywPIf5zswCx793C0iCk9ZgthyNFyYIyi71UhgM3nDKA6HpRrGn6zg8UPqNXpMwYi7hJRbMcBSBd7wyLWTVyM59OJHxCRK7h4Hl7dXNryWfK2Dqwt+qbcimDp6SofffY4w1sXFLq0i2NPYRrOHvY+bUiLxtFbJVORghijHovHHMuI4MomMFAVUurfXM6BoiuYi8dRVfbcMUr2shBJWwzgJNjvAckF0t7dyHhlhKk1iSdiJjw8QT34nac1lex31LMIKqDQyajX+3OYUJWHkRZLbk7HI4XqlCJgJPDW7mcK1RexYf5qlBGTHaVQOqz6h7bvluFWLM2Sw42a5U4wYk0QeuRuFpHjVBYd5vrU5ho8H1i8KzLUhLPnOCXlRX5dnGzQb5d5ny/5nm9s369Xn9es/547f5/TQuuyA==";
    private static final String PROXYNAME = "proxy-dr.reply.it";
    private static final Integer PROXYPORT = 8080;

    public static void main(String[] args) throws COFException {
        VPOSClient vposClient = new VPOSSimpleClient(URL_WEB_API, MAC_KEY_VPOS, API_RESULT_KEY);

        try {
//            vposClient.verifyPayment(buildVerifyTest());
//            vposClient.start3DSAuth(buildAuth3DS());
//            vposClient.start3DSAuthStep2(buildAuth3DSStep2());
//            vposClient.getOrderStatus(buildOrderStatusTest());
//            vposClient.refundPayment(buildRefundTest());
//            vposClient.confirmPayment(buildConfirmTest());
//            vposClient.confirmTransaction(buildBookingTest());
            injectHtmlTemplate(vposClient);
            System.out.println(vposClient.tokenize(SHOP_ID, URL_BACK, URL_DONE, URLMS, URL_REDIRECT));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void injectHtmlTemplate(VPOSClient client) throws COFException {
        client.injectHtmlTemplate(BASE64TEMPLATE, 5000);
    }

    private static PaymentInfo buildPaymentTest() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount("10000");
        paymentInfo.setCurrency("978");
        paymentInfo.setOrderId(Utils.generateRandomDigits(12));
        paymentInfo.setShopId(SHOP_ID);
        paymentInfo.setUrlBack(URL_BACK);
        paymentInfo.setUrlDone(URL_DONE);
        paymentInfo.setUrlMs(URLMS);
        paymentInfo.setAccountingMode("I");
        paymentInfo.setAuthorMode("I");
        paymentInfo.addOption(PaymentInfo.OptionName.G);
        paymentInfo.addOption(PaymentInfo.OptionName.M);
        return paymentInfo;
    }


    private static RefundRequestDto buildRefundTest() {
        return new RefundRequestDto(SHOP_ID, "Giammaicol",
                "8032112928AT1ep4n2gzown54", "726527839399",
                "100", "978", "2", "jopokokok");
    }

    private static Auth3DSDto buildAuth3DS() {
        Auth3DSDto dto = new Auth3DSDto();
        dto.setMasterpass(false);
        dto.setOrderId(Utils.generateRandomDigits(12));
        dto.setPan(PAN_ALIAS);
        dto.setExpDate("2112");
        dto.setAmount("2000");
        dto.setCurrency("978");
        dto.setCvv2("111");
        dto.setAccountingMode("D");
        dto.setNetwork("98");
        //dto.setTimestamp();
        dto.setShopId(SHOP_ID);
        dto.setOperatorId("Giammaicol");
        dto.setInPerson("S");
        dto.setMerchantUrl("http://jnfjdshjfhjd.it");
        return dto;
    }

    private static Auth3DSStep2RequestDto buildAuth3DSStep2() {
        Auth3DSStep2RequestDto dto = new Auth3DSStep2RequestDto();
        dto.setOperatorId(OPERATOR_ID);
        dto.setShopId(SHOP_ID);
        dto.setPaRes("eNqtWMmSo8iy3fMVZXWXWDWzBGXKvMY8CSTmYYcAMYPEIJC+/qHMmrpudVt129NG4Hh4eIT78eMRu/8uTf3hlvZD0bUvH5E/4I8f0jbukqLNXj46tvCJ/Pjf152d92nKWWk89enrTkuHIcrSD0Xy8tEzZM+0+U97Zq9p+721V69vv/GIonUufBI+fXzdHWkzHd7UL1GfDggCY1scRpD105epX9eZ/0B30NfXdY4+zqN2fN1F8ZWR9VcExXBis91uSXIHfZHtmrSXuVcYRv/820HvH3bQdzPH6fk0rO4vRfLqlIHlTVRhnY73LSMRXjbgPJNplga/7KCnxi6JxvQVhdHVOox/gMnPMPKZQHbQm3x3eZqjm25aba8q8A76UbJbN6pf9/H+Sm1Xd7+97dLl0rXpc8wO+va8g747d4nadT3ff89lr7ZX6c72X3dj0fzoFPUZxj8jxA56k++GMRqn4TXYQV+ednF0u72WvEwEcHVqYc1mGZ6m6WV5+6PpdbFvKrs0Ll7hp1Pr/9sous66vhjz5hV71/ku2EFPV6C3qL7urCJr18n69MOaSu3w8jEfx8tnCJrn+Y8Z+6PrM+i5QRBMQatCMhTZfz6+j0oTuT13/2gYG7VdW8RRXTyicU0ULR3zLvnwzbdfmbHNpyUEMnn202rqU4zg7aenBMYQYrUJ/droDyv7nVl+drYfok9DHj1zHPrJ0OvOTM/pMyPSD44pv3z8z//AgiuydBj/zbxf5/zRwld7blRP6euAhI9DBCrmMB6b0KbbstwiMLHfV/HL13Hvmjvom6NfVvEesh+25l0x2gwY20sH7srcpExr6FnB1KuunMqyLoj9Ug63Gt92UKc27qnopdY3LxNCc0sBV8f8rOiMIPZkb/jDY7x7lz6GlSaS6Dm1LU8x8HJzbHRUuCo8d7AJ2xRMyWoosrptmUSQLOuAqk2JKHrZNwZN8AJKqfttQdfn074NB4E/kNl4h3l/NvntqbBkY9xzclEx06EZhDRlxQvf32JcrnikLeH7SfFy/yInOQI+2uhi6FqTZY8Ua0sP0UHd5YcT5Fssa59JLfMyFN5usLlGIqMEx+OW0xJTslEHudwK8+H7cYSHMUIJ56hrHPPGTz6WcQZ5iCYhOXpTfl4Mzxl19pDBRtrcQxk7CoiYoS398vJD6nyJiJre3yPgEzDFRWP0Crw9smk/Fuc1idfiBGiyzNs2y9IPL6NnmaEz2Vh37UY+Kgqr9eZxuMoVoe/5ktaZrLrmVSFSM8zQhiPQHMsAhc3vNboSacThmVxjXVdbJJs+MZnuMnRnCzySx5hZx5Wex42RuSh1P4kmFnlmrVn4DOzpgHMNY8/fXT3wdTj0FS7wlUoWklvcLFWC1lVoMXbo6UjcuNwJNWuZT+pTQd81a5gB1XgzwPF3xQu8RQw8LbMdLXNQt0x8pTZQ6mlAiTEdCX05czC3kHmd0Rjc52weATSuWvSSJrQyQLS6W4UyonHOfLD5u85lj1U2e9yfl2m5xsJxtPpcJvBcJw3XB/PBFxpLvymxixZaDiHJgik4iJHZnvBIRPceoMI9ZBnRchXdsHlfYxwReA6gF+0QePpT6ZGwxCPy+Cn0nTnLVqM0LLLWVbTkE8YZ/HP3aRqXGW6mn99VgO7W0BksfPE8aSgMNRpBVu1PLYud7mbJV01kjGYoIvUVPicYwlc2JcwiM+O3nM3Jcg9MCVEOYZehUHS/VY9waSKSA+kaJAoQXlrn1qVyCwUOBKdXmqpIqjyU9V3dFhw397VHALoLqqJASTCNbrYMUefe6FuHnu9BA0fbCK8sngo2voiNY301RJCnTF0YGO14sdJGHVrAs11CVinZkvaOngZbDCPH7X4L5jSDnC62M3GmiPnawkFXIcDDQ+o+HFioCbkyLEmrWEDjvf0R4Uyi55krmuB41G3x8XSg7tR5oqSrwvDnM8PZqUnI9v0MgxyPrRALGIQo+TYegIk4Dyp+jxk58kRy0ZijTPloy4yRd6IzjaFpsczSdccTeM6i7hnmRJ4tpitZpt2IKxYii8nXl+zx7zABfAXFv8UE8A4KZ5bnL5hYlr/DhHVCKfiJhSzn6e56CYBhTvR8srjN2ERmdXqUHK083ZZMjadLmtYY7W3dimFoGt2JLDuIz1rAzOsXGn9L5YSbeQaaDX4tKELH/aJw0AeWXj8fdXZ/2JIUGd+ulXE5bpIjDDgnlbgdprTrpqMcL0qvOu3DGjAnYSh+DGSKKOfA1Q7JoinmWTzRfEJiGUjY19FAoesGgGBd0RWyd25rIaTmgZyOZ4P3ocwSqbBjna3Ga43jtwOZgpkiiQGdnPht5/TYhERFKgIanqQGOk8o/ciYcs9cyZzXz8dkkSXqcGCbOb3dYvTAMBnn+BzfTFv1wlw2nCZ2lLSVOIAEp7x/4BqC9JRIXKD7fkMKpj44GCu3mBYdNtTeEo8IpUNqSWPjvifSKAjP6NyYKcXHwP4WJYHUFyme5TmOz87GoH2/dW8cVoRK7c08iJj5Yx4eor4O6qFr5Y/kHCdbClvItAB04+UF2EH/U+5/TQDFkwAw8SsBmLSqXr29JtnBqcrAPmyqUCTxeeaMQFG7UM5vsb7GT2AMegayQFvYx3uWMHRg07VrayY8i+8pqPKL+dc5bPMToLHz14pZf62SxnuV9E+YMoRScglF5+fclUIPz7SSr1cDbzn5rKCx2wiD2QilzCOcLOh1gLn30NFvbwYa6pawDGfYdCrM8KKVNKLZ/AJopYFrDyVahXfdfgpXU19kmiwqmjHM7DvLiPysuM6DTzRmePc61xzgb9xe9iU9vO/NYCvO3wD/Z3dN1J1kTl7UB315N6DZch3mJz7MgzujG/fvBQB4rwD1I8bcOi4YO/HkzID532KPJ3kAK3ugDieD6Zhii6jRUn5y7CYtgsMxu0Db0FX5le8aL0TokzjKgTSAwtrMBJPgXq6g2ALhoqUo3gXX8axW2dRJVRg/upK/4FHdGaebHYb+ZvZ1QU7K1CGuF2xWmbY7laZM292oAAIJW5EpHJFRPsLCzdtuwhiGNpMMNsRpFEHk6OU8Lq8bpljUiGRgEwuyhy4I0lw6PoSAizYEXPFAnSuiOTqGi/Vi6IJqitGhKDiekJUeEdUrlNkaMg6itFQbFJ+sc5/d9iN6owESr/3Mv/obPKkislaFhiqPnrGhjxGpb6+1xTATF6KJV6PNKDfLVnR1VIJ4kXVl2K0MIIkHdnMVcNVvH8oUVXeWLpzKl76zBzln6QqkJzZM2F7RNjPZs1iuwOPoA/BWag2Soc/kGqe1lzCCWTLelNd6E/CCQgUXZO9s20kyPUi9Fy25VRvhPm+nNVMv7wYsjc+iNfbhJs+CpsqCYP5dlAJ/BdPfRSnwVzD9XZQC32AayIayubACR7kBGy5iPRbgIWoQ+68ZRTN6DZCq7LEd8nyID0SDqMduczTqujyfHriclSHq0Uly9o78irjLZB6LkqixIjknsD74hglXwFXMWh939mnJHQV4ueQHUvbpGsr7S1bfz05CDcmS7TO0oU1lj9TKsUg2CuL2e1CIhoAHLh64CceTgkOlXE9bEWP0u060ILxJyu2tTpt0W9HJoyQzN1OWcMA3HMKOsRBXqpAaowT0YTd5p/zwYPwovmr1dW9keHitOt2Q9QDuPBJUWdXwWxixcKqL9jeqs/TqLDQwSWwJEtjmzJ3wPUqIzyiJm4eZVegtJ4/FJmRiOKISqSYQqSAFw+/toeA3wkgURKVSJuLJD0sBrOHgtGLFV4/brDZI8cj+AaNwCLcySn//dqT47Tg+jxT/tn36fzpSODPwT9unL0eJ+3pqWFZGuQM6R8Oa++0o8Sb8IvstKAJ/x5i/A0Xg7xjzd6AI/Jkx13Ba8s8NALM2ANzKIEf6GW6jY9dnhj4ybkTuJSC+zLSbJp1MPcJyDjeFs9mEIJvJBbMk6NFDJjstKjfJk4efdkZ0ko3s0tB3BZFn+bQkwKHws/PF9cFgW6m3emI8KffzhrG3g6BRgi5pSeSTQkCjPncHN0zIU8sGSfsTE/TerJ+A82yHaEbKxNWoaDowq71OxfNVcSj8AV+wZHaYODUKswBja3Tkwso999x5bSvFQ8I1GJBtbsfprIHjfSBBwZg0tA1aylUKpFLvmM8lVuEo4Sm94RzoGZNbG0jvCu5yENNqpUcXqNQHKWjSZXN+lEU6oGXpm2pEZH6CzOB5MVXOUZgcKlthSfzizqRIjyDThe68o8B7Og5A8UF5bKy9cW2qO7ISwszTdKSzazf+axax6cOPJAL8kkXkNZl8jwAvjc4ciNzRkfs9KGV5DNGfmwWA+dItrA1C2kzZejhSlCyosthLZTBwwzmjIV0vii1J3rlxgy7wFaoyysJuYtGuRwFA13vDoohWbpfzGQfPkMC2LLhIVze31nzeGJIDesTQVmsZPCdF47unAW1B45JuLv3a2AOohjLHvd8o8rpRmKT0MoRhUzR44ZSzGR5EwUKPm3pttW9OT9GCq9hrR9E3NlhzijaxQAXaFM3KMTrQOBuLB3cl4W7Di52JOxE2o9MZHARpmrdXYZA21Cioo4Mnk1/vyzDBaw/YWB2+Px5Pl02hYgErh7dqPVeonIqOy1XZGDHe1zyuL6p76oaeCJF2a1YsaDYqdgYTcQa2E3a1ThqmMK6U63OYaOB9ptCsz1IczZzgl5UV+nZxs4O+XeZ8v+Z5u7N+u15/XrP+eO3+f2VirSI=");
        dto.setOriginalRefReqNum("20200204437985150517983974419597");
        return dto;
    }

    private static VerifyRequestDto buildVerifyTest() {
        VerifyRequestDto dto = new VerifyRequestDto();
        dto.setOriginalReqRefNum("20200128979407875088471785269188");
        dto.setShopId(SHOP_ID);
        dto.setOperatorId("Giammaicol");
        return dto;
    }

    private static ConfirmRequestDto buildConfirmTest() {
        ConfirmRequestDto dto = new ConfirmRequestDto();
        dto.setShopId(SHOP_ID);
        dto.setOperatorId("Giammaicol");
        dto.setTransactionId("8032112928AT1i3e29q9u4yv4");
        dto.setOrderId("424882487593");
        dto.setAmount("200");
        dto.setCurrency("978");
        dto.setExponent("2");
        dto.setAccountingMode("I");
        dto.setCloseOrder("S");
        return dto;
    }

    private static OrderStatusRequestDto buildOrderStatusTest() {
        return new OrderStatusRequestDto(SHOP_ID, "operator",
                "042221867378323197573301", null, null);
    }

    private static BookingRequestDto buildBookingTest() {
        BookingRequestDto dto = new BookingRequestDto();
        dto.setShopId(SHOP_ID);
        dto.setOperatorId("Giammaicol");
        dto.setTransactionId("8032112928AT2156zcs1dkcb4");
        dto.setOrderId("969206344798");
        dto.setAmount("2000");
        dto.setCurrency("978");
        dto.setExponent("2");
        return dto;
    }



}
