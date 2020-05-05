var elementWidth = parseInt(screen.width / 6);
const cover = document.getElementsByName("cover");
const coverText = document.getElementsByName("coverText");
const coverPlayer = document.getElementsByName("audio");
const audioPlayer = document.getElementsByName("audioPlayer");
const coverVideo = document.getElementsByName("video");
const coverIMG = document.getElementsByName("img");

var uploadButton = document.getElementById("uploadButton");
var upload = document.getElementById("upload");

var sTitle = document.getElementById("title");
var sPlaylist = document.getElementById("playlist");
var sArtist = document.getElementById("artist");
var sOwner = document.getElementById("owner");
var sRelease = document.getElementById("release");
var sGenre = document.getElementById("genre");
var sTags = document.getElementById("tags");

var quit = document.getElementById("quit");

var titleUpload = document.getElementById("mTitle");
var artistUpload = document.getElementById("mArtist");
var tagsUpload = document.getElementById("mTags");
var fileUpload = document.getElementById("mFile");
const thumbnailUpload = document.getElementById("mThumbnail");
const quitUpload = document.getElementById("quitUpload");
var sendUpload = document.getElementById("sendUpload");

var sendLogin = document.getElementById("sendLogin");
var sendSearch = document.getElementById("sendSearch");
var loginName = document.getElementById("name");
var loginPsw = document.getElementById("psw");


var user = document.getElementById("user");
var login = document.getElementById("login");
var search = document.getElementById("setSearch");
var searchTag = document.getElementById("search");
var extSearch = document.getElementById("extSearch");
var head = document.getElementById("coverHead");
var name = document.getElementById("coverName");
var audio = document.getElementById("coverPlayer");
var video = document.getElementById("coverVideo");
var img = document.getElementById("coverImg");
var audioSrc = document.getElementById("coverSrc");
var videoSrc = document.getElementById("videoSrc");
var imgSrc = document.getElementById("imgSrc");

audio.volume = 0.2;
video.volume = 0.2;

var end = false;

console.log(elementWidth);

for (var e of cover)
{
    e.width = elementWidth;
}

for (var e of coverText)
{
    e.style.width = elementWidth + "px";
}

search.addEventListener('click', function()
           {
                if(searchTag.value.replace(" ","").length > 0) window.location.href = "../" + searchTag.value;
                else
                {
                    extSearch.style.top = 0;
                    extSearch.style.opacity = 1;
                }
           });
user.addEventListener('click', function()
           {
                login.style.top = 0;
                login.style.opacity = 1;
           });
quit.addEventListener('click', function()
           {
                login.style.top = -105 + "%";
                login.style.opacity = 0;
           });

sendLogin.addEventListener('click', function()
           {
                var xhttp = new XMLHttpRequest();
                var formData = new FormData();

                formData.append("name",loginName.value);
                formData.append("psw",loginPsw.value);


                xhttp.open("POST","/login");
                xhttp.send(formData);
           });

sendSearch.addEventListener('click', function()
           {
                extSearch.style.top = -105 + "%";
                extSearch.style.opacity = 0;

                if(sTitle.value.replace(" ","").length > 0 ||
                   sPlaylist.value.replace(" ","").length > 0 ||
                   sArtist.value.replace(" ","").length > 0 ||
                   sOwner.value.replace(" ","").length > 0 ||
                   sRelease.value.replace(" ","").length > 0 ||
                   sGenre.value.replace(" ","").length > 0 ||
                   sTags.value.replace(" ","").length > 0)
                   {
                        var out = "../ext";
                        if(sTitle.value.replace(" ","").length > 0) out += "§TITLE," + sTitle.value;
                        if(sPlaylist.value.replace(" ","").length > 0) out += "§PLAYLIST," + sPlaylist.value;
                        if(sArtist.value.replace(" ","").length > 0) out += "§ARTIST," + sArtist.value;
                        if(sOwner.value.replace(" ","").length > 0) out += "§OWNER," + sOwner.value;
                        if(sRelease.value.replace(" ","").length > 0) out += "§RELEASE," + sRelease.value;
                        if(sGenre.value.replace(" ","").length > 0) out += "§GENRE," + sGenre.value;
                        if(sTags.value.replace(" ","").length > 0) out += "§TAGS," + sTags.value;

                        window.location.href = out;
                   }
           });
uploadButton.addEventListener('click', function()
           {
                upload.style.top = 0;
                upload.style.opacity = 1;
           });
quitUpload.addEventListener('click', function()
           {
                upload.style.top = -105 + "%";
                upload.style.opacity = 0;
           });
sendUpload.addEventListener('click', function()
           {
                var formData = new FormData();

                formData.append("media", fileUpload.files[0]);
                formData.append("thumbnail", thumbnailUpload.files[0]);
                formData.append("title", titleUpload.value);
                formData.append("artist", artistUpload.value);
                formData.append("tags", tagsUpload.value);

                var xhr = new XMLHttpRequest();
                xhr.open("POST", "/upload");
                xhr.send(formData);

                upload.style.top = -105 + "%";
                upload.style.opacity = 0;
           });

audioPlayer.forEach(function(a)
           {
                a.addEventListener('click', function()
                    {
                        window.location.href = "../audio_" + a.getAttribute('data');
                    });
           });
coverPlayer.forEach(function(a)
{
    a.addEventListener('click', function()
    {
        head.style.top = 0;
        video.style.top = -105 + "%";
        document.getElementById("coverName").innerHTML = "" + a.getAttribute('title');
        audioSrc.src = 'data/' + a.getAttribute('id');
        video.pause();
        audio.load();
        audio.play();
    });
    audio.addEventListener('ended', function()
    {
        head.style.top = -6 + "em";
    });
});
coverIMG.forEach(function(a)
{
    a.addEventListener('click', function()
    {
        img.style.top = 0;
        video.style.top = -105 + "%";
        head.style.top = -6 + "em";
        video.pause();
        audio.pause();

        imgSrc.src = 'data/' + a.getAttribute('id');
    });
});
coverVideo.forEach(function(a)
{
    a.addEventListener('click', function()
    {
        head.style.top = -6 + "em";
        video.style.top = 0;
        document.getElementById("coverName").innerHTML = "" + a.getAttribute('title');
        videoSrc.src = 'data/' + a.getAttribute('id');
        audio.pause();
        video.load();
        video.play();
    });
    video.addEventListener('ended', function()
    {
        video.style.top = -105 + "%";
        video.webkitExitFullscreen();
    });
    video.addEventListener('pause', function()
    {
        video.style.top = -95 + "%";
    });
    video.addEventListener('play', function()
    {
        video.style.top = 0;
    });
});
img.addEventListener('click', function()
       {
           img.style.top = -105 + "%";
       });