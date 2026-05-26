package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {
  private static List<byte[]> memoryHog = new ArrayList<>();

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @GetMapping("/hello")
  public String hello(@RequestParam(value = "name", defaultValue = "") String name) {
    if (name.isEmpty()) {
      // Úvodní stránka s výběrem jména
      return "<!DOCTYPE html>\n" +
             "<html>\n" +
             "<head>\n" +
             "  <title>Hello - Personální pozdrav</title>\n" +
             "  <style>\n" +
             "    * { margin: 0; padding: 0; box-sizing: border-box; }\n" +
             "    body {\n" +
             "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
             "      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n" +
             "      min-height: 100vh;\n" +
             "      display: flex;\n" +
             "      justify-content: center;\n" +
             "      align-items: center;\n" +
             "      padding: 20px;\n" +
             "    }\n" +
             "    .card {\n" +
             "      background: white;\n" +
             "      border-radius: 20px;\n" +
             "      box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);\n" +
             "      padding: 50px;\n" +
             "      max-width: 500px;\n" +
             "      width: 100%;\n" +
             "      animation: slideIn 0.6s ease-out;\n" +
             "    }\n" +
             "    @keyframes slideIn {\n" +
             "      from {\n" +
             "        opacity: 0;\n" +
             "        transform: translateY(30px);\n" +
             "      }\n" +
             "      to {\n" +
             "        opacity: 1;\n" +
             "        transform: translateY(0);\n" +
             "      }\n" +
             "    }\n" +
             "    h1 {\n" +
             "      color: #667eea;\n" +
             "      margin-bottom: 10px;\n" +
             "      font-size: 32px;\n" +
             "    }\n" +
             "    .subtitle {\n" +
             "      color: #999;\n" +
             "      margin-bottom: 30px;\n" +
             "      font-size: 16px;\n" +
             "    }\n" +
             "    .form-group {\n" +
             "      margin-bottom: 30px;\n" +
             "    }\n" +
             "    label {\n" +
             "      display: block;\n" +
             "      margin-bottom: 10px;\n" +
             "      color: #333;\n" +
             "      font-weight: 600;\n" +
             "      font-size: 14px;\n" +
             "      text-transform: uppercase;\n" +
             "      letter-spacing: 0.5px;\n" +
             "    }\n" +
             "    select {\n" +
             "      width: 100%;\n" +
             "      padding: 15px 15px;\n" +
             "      border: 2px solid #e0e0e0;\n" +
             "      border-radius: 10px;\n" +
             "      font-size: 16px;\n" +
             "      cursor: pointer;\n" +
             "      transition: all 0.3s ease;\n" +
             "      background: white;\n" +
             "      color: #333;\n" +
             "    }\n" +
             "    select:hover {\n" +
             "      border-color: #667eea;\n" +
             "      box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);\n" +
             "    }\n" +
             "    select:focus {\n" +
             "      outline: none;\n" +
             "      border-color: #667eea;\n" +
             "      box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.2);\n" +
             "    }\n" +
             "    .button-group {\n" +
             "      display: flex;\n" +
             "      gap: 10px;\n" +
             "    }\n" +
             "    button {\n" +
             "      flex: 1;\n" +
             "      padding: 15px 30px;\n" +
             "      border: none;\n" +
             "      border-radius: 10px;\n" +
             "      font-size: 16px;\n" +
             "      font-weight: 600;\n" +
             "      cursor: pointer;\n" +
             "      transition: all 0.3s ease;\n" +
             "      text-transform: uppercase;\n" +
             "      letter-spacing: 1px;\n" +
             "    }\n" +
             "    .btn-primary {\n" +
             "      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n" +
             "      color: white;\n" +
             "    }\n" +
             "    .btn-primary:hover {\n" +
             "      transform: translateY(-2px);\n" +
             "      box-shadow: 0 10px 30px rgba(102, 126, 234, 0.3);\n" +
             "    }\n" +
             "    .btn-primary:active {\n" +
             "      transform: translateY(0);\n" +
             "    }\n" +
             "    .btn-secondary {\n" +
             "      background: #f0f0f0;\n" +
             "      color: #333;\n" +
             "    }\n" +
             "    .btn-secondary:hover {\n" +
             "      background: #e0e0e0;\n" +
             "    }\n" +
             "    .emoji {\n" +
             "      font-size: 60px;\n" +
             "      margin-bottom: 20px;\n" +
             "      display: inline-block;\n" +
             "      animation: wave 1s ease-in-out infinite;\n" +
             "    }\n" +
             "    @keyframes wave {\n" +
             "      0%, 100% { transform: rotate(0deg); }\n" +
             "      25% { transform: rotate(20deg); }\n" +
              "        <button type='submit' class='btn-primary' onclick='handleSubmit(event)'>Pozdrav</button>\n" +
              "        <button type='button' class='btn-secondary' onclick=\"window.location.href='http://localhost:8080'\">Zpět</button>\n" +
             "  </style>\n" +
             "</head>\n" +
             "<body>\n" +
             "  <div class='card'>\n" +
             "    <div class='emoji'>👋</div>\n" +
             "    <h1>Ahoj!</h1>\n" +
             "    <p class='subtitle'>Kdo chceš pozdravit?</p>\n" +
             "    <form method='GET' action='/hello'>\n" +
             "      <div class='form-group'>\n" +
             "        <label for='name'>Vyber si jméno:</label>\n" +
             "        <select id='name' name='name' required>\n" +
             "          <option value=''>-- Vybrat jméno --</option>\n" +
             "          <option value='Honzo'>Honzo</option>\n" +
             "          <option value='Pepo'>Pepo</option>\n" +
             "          <option value='Jarda'>Jarda</option>\n" +
             "          <option value='Míša'>Míša</option>\n" +
             "          <option value='Filip'>Filip</option>\n" +
             "          <option value='Tereza'>Tereza</option>\n" +
             "          <option value='Miriam'>Miriam</option>\n" +
             "          <option value='Petra'>Petra</option>\n" +
             "          <option value='Dominik'>Dominik</option>\n" +
             "          <option value='Radka'>Radka</option>\n" +
             "          <option value='Custom'>Vlastní jméno...</option>\n" +
             "        </select>\n" +
             "      </div>\n" +
             "      <div id='customNameGroup' class='form-group' style='display: none;'>\n" +
             "        <label for='customName'>Napiš své jméno:</label>\n" +
             "        <input type='text' id='customName' placeholder='Tvé jméno...' style='width: 100%; padding: 12px 15px; border: 2px solid #e0e0e0; border-radius: 10px; font-size: 16px;'>\n" +
             "      </div>\n" +
             "      <div class='button-group'>\n" +
             "        <button type='submit' class='btn-primary' onclick='handleSubmit(event)'>Pozdrav</button>\n" +
             "        <button type='button' class='btn-secondary' onclick=\"window.location.href='http://localhost:8080'\">Zpět</button>\n" +
             "      </div>\n" +
             "    </form>\n" +
             "  </div>\n" +
             "  <script>\n" +
             "    const selectElem = document.getElementById('name');\n" +
             "    const customGroup = document.getElementById('customNameGroup');\n" +
             "    const customInput = document.getElementById('customName');\n" +
             "    \n" +
             "    selectElem.addEventListener('change', (e) => {\n" +
             "      if (e.target.value === 'Custom') {\n" +
             "        customGroup.style.display = 'block';\n" +
             "        customInput.focus();\n" +
             "      } else {\n" +
             "        customGroup.style.display = 'none';\n" +
             "      }\n" +
             "    });\n" +
             "    \n" +
             "    function handleSubmit(e) {\n" +
             "      e.preventDefault();\n" +
             "      let name = selectElem.value;\n" +
             "      if (name === 'Custom') {\n" +
             "        name = customInput.value.trim();\n" +
             "        if (!name) {\n" +
             "          alert('Prosím napiš své jméno!');\n" +
             "          return;\n" +
             "        }\n" +
             "      }\n" +
             "      if (!name) {\n" +
             "        alert('Prosím vyber jméno!');\n" +
             "        return;\n" +
             "      }\n" +
             "      window.location.href = '/hello?name=' + encodeURIComponent(name);\n" +
             "    }\n" +
             "  </script>\n" +
             "</body>\n" +
             "</html>";
    } else {
      // Pozdravovací stránka s vybraným jménem
      return "<!DOCTYPE html>\n" +
             "<html>\n" +
             "<head>\n" +
             "  <title>Hello " + name + "</title>\n" +
             "  <style>\n" +
             "    * { margin: 0; padding: 0; box-sizing: border-box; }\n" +
             "    body {\n" +
             "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
             "      background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);\n" +
             "      min-height: 100vh;\n" +
             "      display: flex;\n" +
             "      justify-content: center;\n" +
             "      align-items: center;\n" +
             "      padding: 20px;\n" +
             "    }\n" +
             "    .card {\n" +
             "      background: white;\n" +
             "      border-radius: 20px;\n" +
             "      box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);\n" +
             "      padding: 60px;\n" +
             "      max-width: 600px;\n" +
             "      width: 100%;\n" +
             "      text-align: center;\n" +
             "      animation: popIn 0.6s cubic-bezier(0.68, -0.55, 0.265, 1.55);\n" +
             "    }\n" +
             "    @keyframes popIn {\n" +
             "      0% {\n" +
             "        opacity: 0;\n" +
             "        transform: scale(0.5);\n" +
             "      }\n" +
             "      100% {\n" +
             "        opacity: 1;\n" +
             "        transform: scale(1);\n" +
             "      }\n" +
             "    }\n" +
             "    .emoji {\n" +
             "      font-size: 80px;\n" +
             "      margin-bottom: 30px;\n" +
             "      display: inline-block;\n" +
             "      animation: bounce 0.8s ease-in-out infinite;\n" +
             "    }\n" +
             "    @keyframes bounce {\n" +
             "      0%, 100% { transform: translateY(0); }\n" +
             "      50% { transform: translateY(-20px); }\n" +
             "    }\n" +
             "    h1 {\n" +
             "      font-size: 48px;\n" +
             "      color: #f5576c;\n" +
             "      margin-bottom: 10px;\n" +
             "      background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);\n" +
             "      -webkit-background-clip: text;\n" +
             "      -webkit-text-fill-color: transparent;\n" +
             "      background-clip: text;\n" +
             "    }\n" +
             "    .name {\n" +
             "      font-size: 56px;\n" +
             "      font-weight: 900;\n" +
             "      color: #f5576c;\n" +
             "      margin: 20px 0;\n" +
             "      animation: slideInName 0.8s ease-out 0.3s both;\n" +
             "    }\n" +
             "    @keyframes slideInName {\n" +
             "      from {\n" +
             "        opacity: 0;\n" +
             "        transform: translateX(-30px);\n" +
             "      }\n" +
             "      to {\n" +
             "        opacity: 1;\n" +
             "        transform: translateX(0);\n" +
             "      }\n" +
             "    }\n" +
             "    .message {\n" +
             "      font-size: 20px;\n" +
             "      color: #666;\n" +
             "      margin: 30px 0;\n" +
             "      line-height: 1.6;\n" +
             "      animation: fadeIn 0.8s ease-out 0.6s both;\n" +
             "    }\n" +
             "    @keyframes fadeIn {\n" +
             "      from {\n" +
             "        opacity: 0;\n" +
             "      }\n" +
             "      to {\n" +
             "        opacity: 1;\n" +
             "      }\n" +
             "    }\n" +
             "    .button-group {\n" +
             "      margin-top: 40px;\n" +
             "      display: flex;\n" +
             "      gap: 15px;\n" +
             "      animation: fadeIn 0.8s ease-out 0.9s both;\n" +
             "    }\n" +
             "    button {\n" +
             "      flex: 1;\n" +
             "      padding: 15px 30px;\n" +
             "      border: none;\n" +
             "      border-radius: 10px;\n" +
             "      font-size: 16px;\n" +
             "      font-weight: 600;\n" +
             "      cursor: pointer;\n" +
             "      transition: all 0.3s ease;\n" +
             "      text-transform: uppercase;\n" +
             "      letter-spacing: 1px;\n" +
             "    }\n" +
             "    .btn-primary {\n" +
             "      background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);\n" +
             "      color: white;\n" +
             "    }\n" +
             "    .btn-primary:hover {\n" +
             "      transform: translateY(-3px);\n" +
             "      box-shadow: 0 15px 40px rgba(245, 87, 108, 0.4);\n" +
             "    }\n" +
             "    .btn-secondary {\n" +
             "      background: #f0f0f0;\n" +
             "      color: #333;\n" +
             "    }\n" +
             "    .btn-secondary:hover {\n" +
             "      background: #e0e0e0;\n" +
             "    }\n" +
             "  </style>\n" +
             "</head>\n" +
             "<body>\n" +
             "  <div class='card'>\n" +
             "    <div class='emoji'>👋</div>\n" +
             "    <h1>Ahoj</h1>\n" +
             "    <div class='name'>" + name + "!</div>\n" +
             "    <div class='message'>\n" +
             "      Jsem rád, že si do mě zabrouzdal! 🌟\n" +
             "      <br><br>\n" +
             "      Vítej na mé stránce. Doufám, že se ti líbí. 😊\n" +
             "    </div>\n" +
             "    <div class='button-group'>\n" +
             "      <button class='btn-primary' onclick=\"window.location.href='/hello'\">Pozdravit někoho jiného</button>\n" +
             "      <button class='btn-secondary' onclick=\"window.location.href='http://localhost:8080'\">Zpět domů</button>\n" +
             "    </div>\n" +
             "  </div>\n" +
             "</body>\n" +
             "</html>";
    }
  }

  @GetMapping("/")
  public String home() {
    return "<!DOCTYPE html>\n" +
           "<html>\n" +
           "<head>\n" +
           "  <title>Demo App - Vítej!</title>\n" +
           "  <style>\n" +
           "    * { margin: 0; padding: 0; box-sizing: border-box; }\n" +
           "    body {\n" +
           "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
           "      background: linear-gradient(135deg, #667eea 0%, #764ba2 50%, #f093fb 100%);\n" +
           "      min-height: 100vh;\n" +
           "      display: flex;\n" +
           "      flex-direction: column;\n" +
           "    }\n" +
           "    header {\n" +
           "      background: rgba(255, 255, 255, 0.95);\n" +
           "      padding: 20px 40px;\n" +
           "      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);\n" +
           "      display: flex;\n" +
           "      justify-content: space-between;\n" +
           "      align-items: center;\n" +
           "    }\n" +
           "    .logo {\n" +
           "      font-size: 28px;\n" +
           "      font-weight: 900;\n" +
           "      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n" +
           "      -webkit-background-clip: text;\n" +
           "      -webkit-text-fill-color: transparent;\n" +
           "      background-clip: text;\n" +
           "    }\n" +
           "    nav {\n" +
           "      display: flex;\n" +
           "      gap: 30px;\n" +
           "    }\n" +
           "    nav a {\n" +
           "      text-decoration: none;\n" +
           "      color: #333;\n" +
           "      font-weight: 600;\n" +
           "      transition: all 0.3s ease;\n" +
           "      position: relative;\n" +
           "    }\n" +
           "    nav a:hover {\n" +
           "      color: #667eea;\n" +
           "    }\n" +
           "    nav a::after {\n" +
           "      content: '';\n" +
           "      position: absolute;\n" +
           "      bottom: -5px;\n" +
           "      left: 0;\n" +
           "      width: 0;\n" +
           "      height: 2px;\n" +
           "      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n" +
           "      transition: width 0.3s ease;\n" +
           "    }\n" +
           "    nav a:hover::after {\n" +
           "      width: 100%;\n" +
           "    }\n" +
           "    main {\n" +
           "      flex: 1;\n" +
           "      display: flex;\n" +
           "      justify-content: center;\n" +
           "      align-items: center;\n" +
           "      padding: 40px 20px;\n" +
           "    }\n" +
           "    .hero {\n" +
           "      max-width: 800px;\n" +
           "      text-align: center;\n" +
           "      animation: fadeInUp 0.8s ease-out;\n" +
           "    }\n" +
           "    @keyframes fadeInUp {\n" +
           "      from {\n" +
           "        opacity: 0;\n" +
           "        transform: translateY(30px);\n" +
           "      }\n" +
           "      to {\n" +
           "        opacity: 1;\n" +
           "        transform: translateY(0);\n" +
           "      }\n" +
           "    }\n" +
           "    .hero-emoji {\n" +
           "      font-size: 100px;\n" +
           "      margin-bottom: 30px;\n" +
           "      display: inline-block;\n" +
           "      animation: float 3s ease-in-out infinite;\n" +
           "    }\n" +
           "    @keyframes float {\n" +
           "      0%, 100% { transform: translateY(0px); }\n" +
           "      50% { transform: translateY(-20px); }\n" +
           "    }\n" +
           "    h1 {\n" +
           "      font-size: 56px;\n" +
           "      color: white;\n" +
           "      margin-bottom: 20px;\n" +
           "      text-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);\n" +
           "    }\n" +
           "    .subtitle {\n" +
           "      font-size: 24px;\n" +
           "      color: rgba(255, 255, 255, 0.9);\n" +
           "      margin-bottom: 40px;\n" +
           "      line-height: 1.6;\n" +
           "    }\n" +
           "    .cta-buttons {\n" +
           "      display: flex;\n" +
           "      gap: 20px;\n" +
           "      justify-content: center;\n" +
           "      flex-wrap: wrap;\n" +
           "    }\n" +
           "    .btn {\n" +
           "      padding: 18px 40px;\n" +
           "      border: none;\n" +
           "      border-radius: 12px;\n" +
           "      font-size: 18px;\n" +
           "      font-weight: 700;\n" +
           "      cursor: pointer;\n" +
           "      transition: all 0.3s ease;\n" +
           "      text-decoration: none;\n" +
           "      display: inline-block;\n" +
           "      text-transform: uppercase;\n" +
           "      letter-spacing: 1px;\n" +
           "    }\n" +
           "    .btn-primary {\n" +
           "      background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);\n" +
           "      color: white;\n" +
           "      box-shadow: 0 10px 30px rgba(245, 87, 108, 0.3);\n" +
           "    }\n" +
           "    .btn-primary:hover {\n" +
           "      transform: translateY(-5px);\n" +
           "      box-shadow: 0 20px 40px rgba(245, 87, 108, 0.5);\n" +
           "    }\n" +
           "    .btn-secondary {\n" +
           "      background: rgba(255, 255, 255, 0.9);\n" +
           "      color: #667eea;\n" +
           "      box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);\n" +
           "    }\n" +
           "    .btn-secondary:hover {\n" +
           "      background: white;\n" +
           "      transform: translateY(-5px);\n" +
           "      box-shadow: 0 20px 40px rgba(0, 0, 0, 0.2);\n" +
           "    }\n" +
           "    .features {\n" +
           "      margin-top: 60px;\n" +
           "      display: grid;\n" +
           "      grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));\n" +
           "      gap: 30px;\n" +
           "      margin-top: 60px;\n" +
           "    }\n" +
           "    .feature {\n" +
           "      background: rgba(255, 255, 255, 0.1);\n" +
           "      backdrop-filter: blur(10px);\n" +
           "      padding: 30px;\n" +
           "      border-radius: 15px;\n" +
           "      border: 1px solid rgba(255, 255, 255, 0.2);\n" +
           "      color: white;\n" +
           "      transition: all 0.3s ease;\n" +
           "      animation: fadeInUp 0.8s ease-out;\n" +
           "    }\n" +
           "    .feature:nth-child(2) {\n" +
           "      animation-delay: 0.2s;\n" +
           "    }\n" +
           "    .feature:nth-child(3) {\n" +
           "      animation-delay: 0.4s;\n" +
           "    }\n" +
           "    .feature:hover {\n" +
           "      background: rgba(255, 255, 255, 0.2);\n" +
           "      transform: translateY(-10px);\n" +
           "    }\n" +
           "    .feature-emoji {\n" +
           "      font-size: 40px;\n" +
           "      margin-bottom: 15px;\n" +
           "    }\n" +
           "    .feature h3 {\n" +
           "      font-size: 20px;\n" +
           "      margin-bottom: 10px;\n" +
           "    }\n" +
           "    .feature p {\n" +
           "      font-size: 14px;\n" +
           "      opacity: 0.9;\n" +
           "    }\n" +
           "  </style>\n" +
           "</head>\n" +
           "<body>\n" +
           "  <header>\n" +
           "    <div class='logo'>🚀 Demo App</div>\n" +
           "    <nav>\n" +
           "      <a href='/hello'>Pozdrav</a>\n" +
           "      <a href='/prankzone'>Prank</a>\n" +
           "    </nav>\n" +
           "  </header>\n" +
           "  <main>\n" +
           "    <div class='hero'>\n" +
           "      <div class='hero-emoji'>🎉</div>\n" +
           "      <h1>Vítej na Demo App!</h1>\n" +
           "      <p class='subtitle'>Nejlepší místo pro testování a zábavu. Vybírám si, co bude dál! 😄</p>\n" +
           "      <div class='cta-buttons'>\n" +
           "        <a href='/hello' class='btn btn-primary'>Jít na Pozdravy</a>\n" +
           "        <a href='/prankzone' class='btn btn-secondary'>Prank Zone</a>\n" +
           "      </div>\n" +
           "      \n" +
           "      <section class='features' id='prank'>\n" +
           "        <div class='feature'>\n" +
           "          <div class='feature-emoji'>👋</div>\n" +
           "          <h3>Pozdravy</h3>\n" +
           "          <p>Vyber si, koho chceš pozdravit. Personální a hezký zážitek!</p>\n" +
           "        </div>\n" +
           "        <div class='feature'>\n" +
           "          <div class='feature-emoji'>💥</div>\n" +
           "          <h3>Memory Leak Prank</h3>\n" +
           "          <p>Simulace memory leak v prohlížeči až na 10 GB. Jen pro zábavu! 😄</p>\n" +
           "        </div>\n" +
           "        <div class='feature'>\n" +
           "          <div class='feature-emoji'>⚡</div>\n" +
           "          <h3>Bezpečný Test</h3>\n" +
           "          <p>Vše je bezpečně implementováno. Bez rizika pro tvůj počítač!</p>\n" +
           "        </div>\n" +
           "      </section>\n" +
           "    </div>\n" +
           "  </main>\n" +
           "  <script>\n" +
           "  </script>\n" +
           "</body>\n" +
           "</html>";
  }

  // Custom error handler removed to avoid conflicting mapping with Spring Boot's BasicErrorController.
  // To add a custom styled error page without conflicts, add `src/main/resources/templates/error.html`
  // or set `server.error.path` in application properties. Keeping only controller endpoints here.

  @GetMapping("/prankzone")
  public String prankzone() {
    return "<!DOCTYPE html>\n" +
           "<html>\n" +
           "<head>\n" +
           "  <title>Prank Zone</title>\n" +
           "  <style>\n" +
           "    body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: linear-gradient(135deg,#667eea,#764ba2); color: white; min-height: 100vh; display:flex; align-items:center; justify-content:center; }\n" +
           "    .card { background: rgba(255,255,255,0.06); padding: 40px; border-radius: 16px; box-shadow: 0 20px 60px rgba(0,0,0,0.3); max-width: 720px; width: 100%; }\n" +
           "    h1 { margin: 0 0 10px; font-size: 36px; }\n" +
           "    p { margin: 0 0 20px; color: rgba(255,255,255,0.9); }\n" +
           "    .list { display: grid; gap: 10px; }\n" +
           "    a.btn { display: inline-block; padding: 12px 20px; border-radius: 10px; background: linear-gradient(135deg,#f093fb,#f5576c); color: #14142b; font-weight:700; text-decoration:none; }\n" +
           "    .muted { color: rgba(255,255,255,0.8); margin-top: 12px; }\n" +
           "  </style>\n" +
           "</head>\n" +
           "<body>\n" +
           "  <div class='card'>\n" +
           "    <h1>Prank Zone</h1>\n" +
           "    <p>Vyber si prank, který chceš spustit. Memory leak je už připravený.</p>\n" +
              "    <div class='list'>\n" +
                "      <a class='btn' href='/memleak'>🚀 Memory Leak Prank</a>\n" +
                "      <a class='btn' href='/prank1'>🃏 Prank 1 (placeholder)</a>\n" +
                "      <a class='btn' href='/prank2'>🎭 Shatter Screen (Prank 2)</a>\n" +
                "      <a class='btn' href='/prank3'>🎵 Rickroll Me (Prank 3)</a>\n" +
                "    </div>\n" +
           "    <div class='muted'><a href='http://localhost:8080' style='color:inherit;text-decoration:underline'>Zpět domů</a></div>\n" +
           "  </div>\n" +
           "</body>\n" +
           "</html>";
  }

  @GetMapping("/memleak")
  public String memoryLeak() {
    return "<!DOCTYPE html>\n" +
           "<html>\n" +
           "<head>\n" +
           "  <title>Memory Leak - EXTREME EDITION</title>\n" +
           "  <style>\n" +
           "    body { font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background: linear-gradient(135deg, #ff6b6b, #ff8787); transition: all 0.3s; }\n" +
           "    .container { text-align: center; background: white; padding: 40px; border-radius: 10px; box-shadow: 0 5px 20px rgba(0,0,0,0.3); max-width: 600px; }\n" +
           "    h1 { color: #d9534f; margin: 0; font-size: 32px; }\n" +
           "    .memory { font-size: 60px; margin: 20px 0; animation: pulse 1s infinite; }\n" +
           "    @keyframes pulse { 0%, 100% { opacity: 1; } 50% { opacity: 0.5; } }\n" +
           "    @keyframes critical { 0%, 100% { transform: scale(1.1); color: #ff0000; } 50% { transform: scale(0.95); } }\n" +
           "    .progress-bar { width: 100%; height: 40px; background: #ddd; border-radius: 5px; overflow: hidden; margin: 20px 0; }\n" +
           "    .progress-fill { height: 100%; background: linear-gradient(90deg, #ff6b6b, #ff0000); width: 0%; transition: width 0.1s; display: flex; align-items: center; justify-content: center; color: white; font-weight: bold; font-size: 14px; }\n" +
           "    .progress-fill.critical { animation: critical 0.3s infinite; background: linear-gradient(90deg, #ff0000, #000000); }\n" +
           "    #stats { color: #333; font-family: monospace; margin: 15px 0; font-size: 16px; font-weight: bold; }\n" +
           "    p { color: #666; }\n" +
           "    .warning { color: #999; font-size: 12px; margin-top: 15px; }\n" +
           "    .fps { position: fixed; top: 10px; right: 10px; background: rgba(0,0,0,0.7); color: white; padding: 10px; border-radius: 5px; font-family: monospace; }\n" +
           "    .shutdown { animation: shutdown 1s forwards; }\n" +
           "    @keyframes shutdown { 0% { opacity: 1; } 100% { opacity: 0; transform: scale(0.1); } }\n" +
           "  </style>\n" +
           "</head>\n" +
           "<body>\n" +
           "  <div class='fps' id='fps'>FPS: 60</div>\n" +
           "  <div class='container' id='container'>\n" +
           "    <h1 id='title'>💥 MEMORY LEAK EXTREME!</h1>\n" +
           "    <div class='memory' id='emoji'>🐏 💾 🔥</div>\n" +
           "    <p>Plnění paměti až na maximum 10 GB...</p>\n" +
           "    <div class='progress-bar'><div class='progress-fill' id='progress'>0%</div></div>\n" +
           "    <div id='stats'>Paměť: 0 GB / 10 GB | Iterace: 0 / 10240</div>\n" +
           "    <p class='warning'>⚠️ Prohlížeč bude MRTVÝ! Ledaže si to všimne a vypne se sám!</p>\n" +
           "  </div>\n" +
           "\n" +
           "  <script>\n" +
           "    let leakData = [];\n" +
           "    let iteration = 0;\n" +
           "    const maxIterations = 10240; // 10 GB\n" +
           "    const maxMemoryGB = 10;\n" +
           "    const statsDiv = document.getElementById('stats');\n" +
           "    const progressDiv = document.getElementById('progress');\n" +
           "    const titleDiv = document.getElementById('title');\n" +
           "    const emojiDiv = document.getElementById('emoji');\n" +
           "    const fpsDiv = document.getElementById('fps');\n" +
           "    const container = document.getElementById('container');\n" +
           "    let lastTime = performance.now();\n" +
           "    let frameCount = 0;\n" +
           "    let running = true;\n" +
           "    \n" +
           "    function updateFPS() {\n" +
           "      frameCount++;\n" +
           "      const now = performance.now();\n" +
           "      if (now >= lastTime + 1000) {\n" +
           "        fpsDiv.textContent = `FPS: ${frameCount}`;\n" +
           "        frameCount = 0;\n" +
           "        lastTime = now;\n" +
           "      }\n" +
           "      if (running) requestAnimationFrame(updateFPS);\n" +
           "    }\n" +
           "    updateFPS();\n" +
           "    \n" +
           "    function emergencyShutdown() {\n" +
           "      running = false;\n" +
           "      titleDiv.textContent = '💀 MAXIMUM DOSAŽENO - VYPÍNÁM SE!';\n" +
           "      emojiDiv.textContent = '💥💥💥';\n" +
           "      progressDiv.classList.add('critical');\n" +
           "      progressDiv.textContent = '100%';\n" +
           "      progressDiv.style.width = '100%';\n" +
           "      \n" +
           "      // Vyčistit data\n" +
           "      setTimeout(() => {\n" +
           "        leakData = [];\n" +
           "        container.classList.add('shutdown');\n" +
           "        setTimeout(() => {\n" +
           "          statsDiv.textContent = 'Paměť vyčištěna. Zavírám stránku...';\n" +
           "          setTimeout(() => {\n" +
           "            window.history.back();\n" +
           "          }, 2000);\n" +
           "        }, 500);\n" +
           "      }, 1000);\n" +
           "    }\n" +
           "    \n" +
           "    function startMemoryLeak() {\n" +
           "      const leakInterval = setInterval(() => {\n" +
           "        if (iteration >= maxIterations || !running) {\n" +
           "          clearInterval(leakInterval);\n" +
           "          if (running) emergencyShutdown();\n" +
           "          return;\n" +
           "        }\n" +
           "        \n" +
           "        // Vytváříme velké pole textu (každá iterace cca 1 MB)\n" +
           "        const chunk = new Array(120000);\n" +
           "        for (let i = 0; i < 120000; i++) {\n" +
           "          chunk[i] = 'X'.repeat(1200) + Math.random().toString() + Math.random().toString();\n" +
           "        }\n" +
           "        leakData.push(chunk);\n" +
           "        \n" +
           "        iteration++;\n" +
           "        const memoryUsedGB = (iteration / 1024).toFixed(2);\n" +
           "        const percentage = (iteration / maxIterations) * 100;\n" +
           "        \n" +
           "        statsDiv.textContent = `Paměť: ${memoryUsedGB} GB / ${maxMemoryGB} GB | Iterace: ${iteration} / ${maxIterations} (${Math.round(percentage)}%)`;\n" +
           "        progressDiv.style.width = percentage + '%';\n" +
           "        progressDiv.textContent = Math.round(percentage) + '%';\n" +
           "        \n" +
           "        // Kritická situace přibližuje se\n" +
           "        if (percentage > 70) {\n" +
           "          titleDiv.style.color = '#ff0000';\n" +
           "          emojiDiv.textContent = '🔴 🔴 🔴';\n" +
           "          if (!progressDiv.classList.contains('critical') && percentage > 85) {\n" +
           "            progressDiv.classList.add('critical');\n" +
           "          }\n" +
           "        }\n" +
           "        \n" +
           "        // Pokus detekovat paměťový tlak z performance.memory (Chrome)\n" +
           "        if (performance.memory && performance.memory.jsHeapSizeLimit) {\n" +
           "          const heapUsed = performance.memory.usedJSHeapSize / (1024 * 1024 * 1024);\n" +
           "          const heapLimit = performance.memory.jsHeapSizeLimit / (1024 * 1024 * 1024);\n" +
           "          if (heapUsed > heapLimit * 0.95) {\n" +
           "            console.warn(`KRITICKÉ: Heap na ${(heapUsed / heapLimit * 100).toFixed(1)}%`);\n" +
           "            emergencyShutdown();\n" +
           "            return;\n" +
           "          }\n" +
           "        }\n" +
           "        \n" +
           "        console.log(`Memory leak - ${iteration}/${maxIterations} MB (${percentage.toFixed(1)}%)`);\n" +
           "      }, 50); // Více iterací za sekundu\n" +
           "    }\n" +
           "    \n" +
           "    // Spustit leak ihned\n" +
           "    startMemoryLeak();\n" +
           "  </script>\n" +
           "</body>\n" +
           "</html>";
  }

  @GetMapping("/prank2")
  public String prank2() {
    return "<!DOCTYPE html>\n" +
           "<html>\n" +
           "<head>\n" +
           "  <title>Shatter Screen Prank</title>\n" +
           "  <meta name='viewport' content='width=device-width,initial-scale=1'>\n" +
           "  <style>\n" +
           "    html,body{height:100%;margin:0;}\n" +
           "    body{background:linear-gradient(135deg,#14142b,#3a3f7a);color:white;overflow:hidden;font-family:Segoe UI,Arial,sans-serif;}\n" +
           "    .center{position:absolute;left:50%;top:40%;transform:translate(-50%,-50%);text-align:center}\n" +
           "    .hint{opacity:0.9}\n" +
           "    .shard{position:absolute;width:60px;height:60px;background:rgba(255,255,255,0.03);backdrop-filter: blur(2px);border-radius:3px;box-shadow:0 2px 10px rgba(0,0,0,0.6);transition:transform 1.2s cubic-bezier(.2,.9,.2,1),opacity 1.2s;}\n" +
           "    @keyframes shake{0%{transform:translateX(0)}20%{transform:translateX(-8px)}40%{transform:translateX(8px)}60%{transform:translateX(-6px)}80%{transform:translateX(4px)}100%{transform:translateX(0)}}\n" +
           "    .shake{animation:shake 0.8s ease-in-out}\n" +
           "    .back{position:fixed;left:12px;top:12px;padding:8px 12px;background:#fff;color:#111;border-radius:8px;text-decoration:none;font-weight:700;z-index:999;}\n" +
           "  </style>\n" +
           "</head>\n" +
           "<body>\n" +
           "  <a class='back' href='/prankzone'>Zpět</a>\n" +
           "  <div class='center'>\n" +
           "    <h1>🎭 Shatter Screen</h1>\n" +
           "    <p class='hint'>Počkej chvíli... obrazovka se začne tříštit.</p>\n" +
           "  </div>\n" +
           "  <div id='overlay'></div>\n" +
           "  <script>\n" +
           "    const overlay = document.getElementById('overlay');\n" +
           "    const count = 48;\n" +
           "    const w = window.innerWidth, h = window.innerHeight;\n" +
           "    for (let i=0;i<count;i++){\n" +
           "      const s = document.createElement('div');\n" +
           "      s.className='shard';\n" +
           "      const size = 30 + Math.random()*160;\n" +
           "      s.style.width = size + 'px'; s.style.height = size + 'px';\n" +
           "      s.style.left = (Math.random()*100)+'%'; s.style.top = (Math.random()*100)+'%';\n" +
           "      s.style.transform = 'translate(-50%,-50%) rotate('+ (Math.random()*360).toFixed(1) +'deg)';\n" +
           "      s.style.opacity = 1;\n" +
           "      document.body.appendChild(s);\n" +
           "    }\n" +
           "    // small delay then explode shards outward\n" +
           "    setTimeout(()=>{\n" +
           "      document.body.classList.add('shake');\n" +
           "      const shards = document.querySelectorAll('.shard');\n" +
           "      shards.forEach(s=>{\n" +
           "        const dx = (Math.random()-0.5)* (w*1.6);\n" +
           "        const dy = (Math.random()-0.5)* (h*1.6);\n" +
           "        const rz = (Math.random()-0.5)*1080;\n" +
           "        s.style.transform = 'translate('+dx+'px,'+dy+'px) rotate('+rz+'deg)';\n" +
           "        s.style.opacity = 0;\n" +
           "      });\n" +
           "    },700);\n" +
           "    // after animation show message and allow going back\n" +
           "    setTimeout(()=>{\n" +
           "      const msg = document.createElement('div');\n" +
           "      msg.style.position='fixed'; msg.style.left='50%'; msg.style.top='50%'; msg.style.transform='translate(-50%,-50%)'; msg.style.color='white'; msg.style.fontSize='20px'; msg.style.background='rgba(0,0,0,0.6)'; msg.style.padding='12px 18px'; msg.style.borderRadius='10px'; msg.style.zIndex='999'; msg.textContent='Hotovo — to byla prasklá obrazovka!';\n" +
           "      document.body.appendChild(msg);\n" +
           "    },2200);\n" +
           "  </script>\n" +
           "</body>\n" +
           "</html>";
  }

  @GetMapping("/prank3")
  public String prank3() {
    return "<!DOCTYPE html>\n" +
           "<html>\n" +
           "<head>\n" +
           "  <title>Rickroll</title>\n" +
           "  <meta name='viewport' content='width=device-width,initial-scale=1'>\n" +
           "  <style>body,html{height:100%;margin:0;background:black} .back{position:fixed;left:12px;top:12px;padding:8px 12px;background:#fff;color:#111;border-radius:8px;text-decoration:none;font-weight:700;z-index:999} iframe{position:absolute;left:50%;top:50%;transform:translate(-50%,-50%);width:100%;height:100%;border:0}</style>\n" +
           "</head>\n" +
           "<body>\n" +
           "  <a class='back' href='/prankzone'>Zpět</a>\n" +
           "  <iframe src='https://www.youtube.com/embed/dQw4w9WgXcQ?autoplay=1&controls=0&rel=0&modestbranding=1' allow='autoplay; encrypted-media' allowfullscreen></iframe>\n" +
           "</body>\n" +
           "</html>";
  }
}