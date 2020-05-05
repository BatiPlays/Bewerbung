var state = 1;
var sw = window.screen.width;
var sh = window.screen.height;
var sr;

function ggt(a,b)
  {
   var r;
   do
    {
      r = a % b;
      a=b;
      b=r;
    }
   while (r>0);
     sr = (sw / a) + ":" + (sh / a);
        var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
}


if(state == 1)
{
    main.classList.remove("fa-user-o");
    main.classList.add("fa-user");
}

window.onload = ggt(sw,sh);
window.onresize = function()
{
    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
};