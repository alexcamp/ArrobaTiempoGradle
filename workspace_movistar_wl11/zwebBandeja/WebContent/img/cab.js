if(window.location.href.indexOf('mapa_web')!=-1){
 document.write('<span class="enlcabportadaOn">Mapa Web</span>');
}else{
 document.write('');
}

if(window.location.href.indexOf('contactenos')!=-1){
 document.write(' | <span class="enlcabportadaOn">Cont&aacute;ctanos</span>');
}else{
 document.write('');
}

if(window.location.href.indexOf('empresas_grupo')!=-1){
 document.write(' | <span class="enlcabportadaOn">Empresas del Grupo</span>');
}else{
 document.write('');
}
