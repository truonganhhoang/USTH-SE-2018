let map;
let markers = [];
let info = new google.maps.InfoWindow();
let places = [
    { "place_name": "Nghệ An", "keyword": "Nghe An", "geo": "Nghe+An;+Vietnam", "latitude": 19.2342, "longitude": 104.9200 },
    { "place_name": "Đắk Lắk", "keyword": "Dak Lak", "geo": "Dak+Lak;+Vietnam", "latitude": 12.7100, "longitude": 108.2378 },
    { "place_name": "Bình Dương", "keyword": "Binh Duong", "geo": "Binh+Duong;+Vietnam", "latitude": 11.3254, "longitude": 106.4770 },
    { "place_name": "Quảng Ninh", "keyword": "Quang Ninh", "geo": "Quang+Ninh;+Vietnam", "latitude": 21.0064, "longitude": 107.2925 },
    { "place_name": "Thanh Hoa", "keyword": "Thanh Hoa", "geo": "Thanh+Hoa;+Vietnam", "latitude": 20.1291, "longitude": 105.3131 },
    { "place_name": "Ha Giang", "keyword": "Ha Giang", "geo": "Ha+Giang;+Vietnam", "latitude": 22.7662, "longitude": 104.9389 },
    { "place_name": "Phú Yên", "keyword": "Phu Yen", "geo": "Phu+Yen;+Vietnam", "latitude": 13.0882, "longitude": 109.0929 },
    { "place_name": "Đồng Nai", "keyword": "Dong Nai", "geo": "Dong+Nai;+Vietnam", "latitude": 11.0686, "longitude": 107.1676 },
    { "place_name": "Gia Lai", "keyword": "Gia Lai", "geo": "Gia+Lai;+Vietnam", "latitude": 13.8079, "longitude": 108.1094 },
    { "place_name": "Ha Tinh", "keyword": "Ha Tinh", "geo": "Ha+Tinh;+Vietnam", "latitude": 18.2944, "longitude": 105.6745 },
    { "place_name": "Ninh Bình", "keyword": "Ninh Bình", "geo": "Ninh+Bình;+Vietnam", "latitude": 20.2130, "longitude": 105.9230 },
    { "place_name": "Đồng Tháp", "keyword": "Đồng Tháp", "geo": "Đồng+Tháp;+Vietnam", "latitude": 10.4938, "longitude": 105.6882 },
    { "place_name": "Kiên Giang", "keyword": "Kiên Giang", "geo": "Kiên+Giang;+Vietnam", "latitude": 9.8250, "longitude": 105.1259 },
    { "place_name": "Binh Dinh", "keyword": "Binh Dinh", "geo": "Binh+Dinh;+Vietnam", "latitude": 14.1665, "longitude": 108.9027 },
    { "place_name": "Bac Ninh", "keyword": "Bac Ninh", "geo": "Bac+Ninh;+Vietnam", "latitude": 21.1214, "longitude": 106.1111 },
    { "place_name": "An Giang", "keyword": "An Giang", "geo": "An+Giang;+Vietnam", "latitude": 10.5216, "longitude": 105.1259 },
    { "place_name": "Vinh Phuc", "keyword": "Vinh Phuc", "geo": "Vinh+Phuc;+Vietnam", "latitude": 21.3609, "longitude": 105.5474 },
    { "place_name": "Bến Tre", "keyword": "Bến Tre", "geo": "Bến+Tre;+Vietnam", "latitude": 10.1082, "longitude": 106.4406 },
    { "place_name": "Khanh Hoa", "keyword": "Khanh Hoa", "geo": "Khanh+Hoa;+Vietnam", "latitude": 12.2585, "longitude": 109.0526 },
    { "place_name": "Long An", "keyword": "Long An", "geo": "Long+An;+Vietnam", "latitude": 10.6956, "longitude": 106.2431 },
    { "place_name": "Bình Thuận", "keyword": "Bình Thuận", "geo": "Bình+Thuận;+Vietnam", "latitude": 11.0904, "longitude": 108.0721 },
    { "place_name": "Quảng Ngãi", "keyword": "Quảng Ngãi", "geo": "Quảng+Ngãi;+Vietnam", "latitude": 15.0760, "longitude": 108.7126 },
    { "place_name": "Ba Ria–Vung Tau", "keyword": "Ba Ria–Vung Tau", "geo": "Ba Ria–Vung+Tau;+Vietnam", "latitude": 10.5417, "longitude": 107.2430 },
    { "place_name": "Quang Binh", "keyword": "Quang Binh", "geo": "Quang+Binh;+Vietnam", "latitude": 17.6103, "longitude": 106.3487 },
    { "place_name": "Thai Binh", "keyword": "Thai Binh", "geo": "Thai+Binh;+Vietnam", "latitude": 20.5387, "longitude": 106.3935 },
    { "place_name": "Quang Nam", "keyword": "Quang Nam", "geo": "Quang+Nam;+Vietnam", "latitude": 15.5394, "longitude": 108.0191 },
    { "place_name": "Hải Dương", "keyword": "Hải Dương", "geo": "Hải+Dương;+Vietnam", "latitude": 20.9386, "longitude": 106.3207 },
    { "place_name": "Ninh Thuan", "keyword": "Ninh Thuan", "geo": "Ninh+Thuan;+Vietnam", "latitude": 11.6739, "longitude": 108.8630 },
    { "place_name": "Tien Giang", "keyword": "Tien Giang", "geo": "Tien+Giang;+Vietnam", "latitude": 10.4493, "longitude": 106.3421 },
    { "place_name": "Hà Nam", "keyword": "Hà Nam", "geo": "Hà+Nam;+Vietnam", "latitude": 20.5835, "longitude": 105.9230 },
    { "place_name": "Cao Bang", "keyword": "Cao Bang", "geo": "Cao+Bang;+Vietnam", "latitude": 22.6357, "longitude": 106.2522 },
    { "place_name": "Bac Giang", "keyword": "Bac Giang", "geo": "Bac+Giang;+Vietnam", "latitude": 21.3015, "longitude": 106.6291 },
    { "place_name": "Hung Yen", "keyword": "Hung Yen", "geo": "Hung+Yen;+Vietnam", "latitude": 20.8526, "longitude": 106.0170 },
    { "place_name": "Dak Nong", "keyword": "Dak Nong", "geo": "Dak+Nong;+Vietnam", "latitude": 12.2646, "longitude": 107.6098 },
    { "place_name": "Lao Cai", "keyword": "Lao Cai", "geo": "Lao+Cai;+Vietnam", "latitude": 22.3381, "longitude": 104.1487 },
    { "place_name": "Ca Mau", "keyword": "Ca Mau", "geo": "Ca+Mau;+Vietnam", "latitude": 8.9624, "longitude": 105.1259 },
    { "place_name": "Hau Giang", "keyword": "Hau Giang", "geo": "Hau+Giang;+Vietnam", "latitude": 9.7579, "longitude": 105.6413 },
    { "place_name": "Bac Lieu", "keyword": "Bac Lieu", "geo": "Bac+Lieu;+Vietnam", "latitude": 9.2516, "longitude": 105.5136 },
    { "place_name": "Binh Phuoc", "keyword": "Binh Phuoc", "geo": "Binh+Phuoc;+Vietnam", "latitude": 11.7512, "longitude": 106.7235 },
    { "place_name": "Quảng Trị", "keyword": "Quảng Trị", "geo": "Quảng+Trị;+Vietnam", "latitude": 16.7943, "longitude": 106.9634 },
    { "place_name": "Nam Dinh", "keyword": "Nam Dinh", "geo": "Nam+Dinh;+Vietnam", "latitude": 20.2792, "longitude": 106.2051 },
    { "place_name": "Thừa Thiên-Huế", "keyword": "Thừa Thiên-Huế", "geo": "Thừa+Thiên-Huế;+Vietnam", "latitude": 16.4674, "longitude": 107.5905 },
    { "place_name": "Lam Dong", "keyword": "Lam Dong", "geo": "Lam+Dong;+Vietnam", "latitude": 11.5753, "longitude": 108.1429 },
    { "place_name": "Yen Bai", "keyword": "Yen Bai", "geo": "Yen+Bai;+Vietnam", "latitude": 21.6838, "longitude": 104.4551 },
    { "place_name": "Tây Ninh", "keyword": "Tây Ninh", "geo": "Tây+Ninh;+Vietnam", "latitude": 11.3495, "longitude": 106.0640 },
    { "place_name": "Vinh Long", "keyword": "Vinh Long", "geo": "Vinh+Long;+Vietnam", "latitude": 10.0861, "longitude": 106.0170 },
    { "place_name": "Tuyên Quang", "keyword": "Tuyên Quang", "geo": "Tuyên+Quang;+Vietnam", "latitude": 22.1727, "longitude": 105.3131 },
    { "place_name": "Kon Tum", "keyword": "Kon Tum", "geo": "Kon+Tum;+Vietnam", "latitude": 14.6612, "longitude": 107.8389 },
    { "place_name": "Phu Tho", "keyword": "Phu Tho", "geo": "Phu+Tho;+Vietnam", "latitude": 21.2684, "longitude": 105.2046 },
    { "place_name": "Lang Son", "keyword": "Lang Son", "geo": "Lang+Son;+Vietnam", "latitude": 21.8564, "longitude": 106.6291 },
    { "place_name": "Thai Nguyen", "keyword": "Thai Nguyen", "geo": "Thai+Nguyen;+Vietnam", "latitude": 21.5614, "longitude": 105.8760 }
];
$(function () {
    let styles = [
        {
            "elementType": "labels",
            "stylers": [
                {
                    "visibility": "off"
                }
            ]
        },
        {
            "featureType": "administrative.land_parcel",
            "stylers": [
                {
                    "visibility": "off"
                }
            ]
        },
        {
            "featureType": "administrative.neighborhood",
            "stylers": [
                {
                    "visibility": "off"
                }
            ]
        },
        {
            "featureType": "road",
            "stylers": [
                {
                    "visibility": "off"
                }
            ]
        }
    ];
    let options = {
        center: {
            lat: 21.028511,
            lng: 105.804817
        },
        disableDefaultUI: true,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        maxZoom: 9,
        minZoom: 7,
        panControl: true,
        styles: styles,
        zoom: 8,
        zoomControl: true
    };
    let canvas = $("#map-canvas").get(0);
    map = new google.maps.Map(canvas, options);
    google.maps.event.addListenerOnce(map, "idle", configure)
});

function addMarker(place) {
    let marker = new MarkerWithLabel({
        icon: "http://maps.google.com/mapfiles/kml/pal2/icon31.png",
        position: new google.maps.LatLng(place.latitude, place.longitude),
        map: map,
        labelContent: place.place_name,
        labelAnchor: new google.maps.Point(20, 0),
        labelClass: "label"
    });
    google.maps.event.addListener(marker, "click", function () {
        map.setCenter({
            lat: place.latitude,
            lng: place.longitude
        });
        // showInfo(marker);
        x = Math.random();
        ul = "<h5>";
        if (x < 0.2)
            ul += "Some great <em>cat</em> videos";
        else if (x < 0.4)
            ul += "Here are <u>cat</u> videos";
        else if (x < 0.6)
            ul += "<em>Cat</em> videos you might like";
        else if (x < 0.8)
            ul += "Let's watch some <em>cat</em> videos";
        else
            ul += "Entertaining <u>cat</u> videos"
        ul += " from " + place.place_name + ", Viet Nam";
        ul += "</h5>"
        ul += "<ul>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=Qit3ALTelOo&feature=youtu.be' target='_blank'>One man's cat was such a warrior that he named it Sparta and wrote a song in its honor.</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=0Bmhjf0rKe8&feature=youtu.be' target='_blank'>A kitten is tickled. Unfathomable cuteness ensues.</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=z3U0udLH974' target='_blank'>Two cats `talk' to one another. Tens of millions listen.</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=Vw4KVoEVcr0&feature=youtu.be' target='_blank'> A mother cat embraces her sleepy kin.</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=_ZSbC09qgLI' target='_blank'>From a mailbox to a coffee pot, a tiny kitty has a lion's share of hiding places.</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=fzzjgBAaWZw&feature=youtu.be' target='_blank'> Watch a saucer-eyed cat's sneaky pursuit of its prey -- the camera.</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=J---aiyznGQ' target='_blank'>This cool cat hits all the right notes.</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=TZ860P4iTaM&feature=youtu.be' target='_blank'> Another cat tickles the ivories, but this time the video posters insist it's not a trick.</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=964uCtgsDoE&feature=youtu.be' target='_blank'>Why bother meowing when you can make noises that sound disturbingly like real speech?</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=C_S5cXbXe-4&feature=youtu.be' target='_blank'>What could possibly have prompted Choco the cat's `gobsmacked' reaction?</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=X3iFhLdWjqc&feature=youtu.be' target='_blank'>A human voiceover turns two cats pawing at each other into a chuckle-worthy game of feline patty-cake.</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=hPzNl6NKAG0&feature=youtu.be' target='_blank'>A cat slides headfirst through a box and into our hearts.</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=TJ5UJv9A4jE&feature=related' target='_blank'> A cat stretches and `talks' after napping.</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=eV71mpbvl-g&feature=related' target='_blank'> Is this cat really saying `hello'?</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=gCH68g_R5Iw&feature=related' target='_blank'>Crawling baby, pouncing kitty: It's an attack, all right -- of adorable proportions.</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=S5-D0f6nHSQ&feature=related' target='_blank'>The video's name indicates an act of maternal love, but some YouTube commenters wondered whether the mother cat was a bit hungry.</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?feature=fvwp&v=QT2NOQ0P4t8&NR=1' target='_blank'>Thanks to another human voiceover, this sleeping cat mumbles with the best of them.</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=598IdFlOXcQ' target='_blank'>Forget an expensive day at the spa: See if you can book this kitty masseuse for your next rubdown.</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=HECa3bAFAYk&feature=fvwrel' target='_blank'>A shiny toy has this kitten on alert.</a></li>";
        if (Math.random() < 0.25) ul += "<li><a href='http://www.youtube.com/watch?v=tcxhOGyrCtI' target='_blank'>This cat may seem menacing at first, but a little squeak suggests otherwise.</a></li>";
        ul += "</ul>";
        showInfo(marker, ul);
    });
    markers.push(marker)
}

function configure() {
    $("#q").typeahead({
       highlight: false,
       minLength: 1
     },
                       {
                         display: function (suggestion) {
                           return null
                         },
                         limit: 10,
                         source: search,
                         templates: {
                           suggestion: Handlebars.compile("<div>" + "{{place_name}}" + "</div>")
                         }
                       });
     $("#q").on("typeahead:selected", function (eventObject, suggestion, name) {
       map.setCenter({
         lat: parseFloat(suggestion.latitude),
         lng: parseFloat(suggestion.longitude)
       });
     });
    $("#q").focus(function (eventData) {
        info.close()
    });
    init();
    // $("#q").focus()
}

function removeMarkers() {
    for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(null)
    }
    markers.length = 0
}

function search(query, syncResults, asyncResults) {
    let data = [];
    let re = new RegExp("^" + query, "i");
    for (let i = 0; i < places.length; i++) {
        if (places[i].place_name.match(re) || places[i].keyword.match(re)) {
            data.push(places[i]);
        }
    }
    syncResults(data);
}

function showInfo(marker, content) {
    let div = "<div id='info'>";
    if (typeof(content) == "undefined") {
        div += "<img alt='loading' src='/static/ajax-loader.gif'/>"
    } else {
        div += content
    }
    div += "</div>";
    info.setContent(div);
    info.open(map, marker)
}

function init() {
    for (let i = 0; i < places.length; i++)
        addMarker(places[i]);
}
