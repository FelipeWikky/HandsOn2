<h1>HandsOn</h1>

<p><b>Projeto desenvolvido em Android Nativo</b></p>

<p>
  Projeto idealizado como alternativa para contenção do vírus Covid-19.
</p>

<p>
  Através deste aplicativo, o paciente poderá ser aconselhado previamente sobre a necessidade de buscar atendimento médico para tratamento, ser posto em quarentena, ou sem suspeita do vírus (liberado), com base nos dados informados.
</p>

<h2>Especificações do Projeto</h2>
<ul>
  <li>Este projeto foi desenvolvido e compilado com base no Android versão 9.0 (API 28) </li>
  <li>A aplicação suporta dispostivos Android versão 8.0 (API 26) ou superior</li>
  <li>Os dados armazenados são salvos no próprio dispositivo instalado</li>
</ul>

<h2>Configuração do Ambiente</h2>
  <p>Para visualizar o projeto, é necessário que possua o Android Studio instalado.</br>
  Caso não o tenha, <a href="https://developer.android.com/studio?hl=pt-br">clique aqui</a> para baixá-lo. Quando concluído, instale-o.
  </p>
  <p>Após instalado o Android Studio, baixe o projeto do Github (<a href="https://github.com/FelipeWikky/HandsOn2">aqui</a>) e abra-o na IDE</p>
  <p>Para inicializar o projeto poderá usar um aparelho Android com versão 8.0 (API 26) ou superior para executar o app.</p>
  <p>O Android Studio também disponibiliza um Emulador, que pode ser configurado na própria IDE. Maiores informações, <a href="https://developer.android.com/studio/run/emulator?hl=pt-br">clique aqui</a>.</p>
  
<h2>Gerando APK de Instalação</h2>  
  <p>Caso realizado alguma alteração no projeto, poderá gerar um novo arquivo .APK com estas alterações salvas para que possa instalar em outros dispositivos.</p>
  <p>Para isso, é necessário ter o Android Studio instalado pois é através dele onde será gerado um novo APK.</p>
  <p>O Android exige que todo app seja assinado digitalmente com um certificado antes de serem instalados em um dispositivo. Maiores informações, você encontra <a href="https://developer.android.com/studio/publish/app-signing?hl=pt-br">aqui</a>.</p>
  <p>Através do Android Studio, você poderá gerar uma assinatura digital para o app.<br/>
    Para isso, com o projeto aberto no Android Studio, siga as instruições disponibilizada na documentação do próprio Android:<br/>
    <a href="https://developer.android.com/studio/publish/app-signing?hl=pt-br#generate-key">Gerando Chave de Assinatura</a>
    e 
    <a href="https://developer.android.com/studio/publish/app-signing?hl=pt-br#sign_release">Assinando o app com a Chave registrada</a>.</p>

<h2>Instruções de Uso do App</h2>
<p>Para executar este aplicativo no seu aparelho celular e usar suas funcionalidades, siga este procedimento.</p>
<ul>
  <li>Baixe o APK disponibilizado <a href="https://github.com/FelipeWikky/HandsOn2/tree/master/installation">aqui</a> </li>
  <li>Instale o APK no seu celular</li>
  <li>Após instalado e inicializado o app, clique no Menu superior a Esquerda para exibir as opções:</li>
    <ol>
      <li>Cadastrar Prontuário</li>
        <p>Nesta tela, deverá ser informado os campos obrigatórios (*) e os campos adicionais, conforme caracteristicas do paciente.</p>
        <p>Após informados todos campos, clique em Cadastrar localizado no canto superior a direita.</p>
        <p>Feito isso, uma breve notificação sobre o Paciente cadastrado será informada em tela para os casos onde:<br/>
          1. Esteja liberado<br/>
          2. Necessite ficar em quarentena<br/>
          3. Deverá ser Internado<br/>
        </p>
      <p>Para visualizar os dados do Paciente cadastrado, utilize o Menu para ir até a Lista de prontuários.</p>
      <li>Listar Prontuários</li>
        <p>Nesta tela, é listado todos os pacientes já cadastrados no aplicativo</p>
        <p>Através das opções disponíveis por meio de botões, poderá filtrar os pacientes cadastrados com base nos diagnósticos descritos:</br>
          <b>Liberado</b><br/>
          <b>Quarentena</b><br/>
          <b>Internado</b><br/>
          Clique na opção correspondente, e listará apenas os pacientes com o filtro escolhido.</br>
          Caso deseje visualizar novamente todos os prontuários (sem filtro), clique novamente no filtro a qual selecionou.
        </p>
    <ol>
</ul>
  
