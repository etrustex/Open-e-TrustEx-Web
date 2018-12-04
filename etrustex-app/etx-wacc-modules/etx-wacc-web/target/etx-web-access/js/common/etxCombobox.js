// customisation of http://jqueryui.com/autocomplete/#combobox
(function ($) {

    $.widget("custom.combobox", {
      customChangeSelection:"",
      customDisableSomeParts:"",
        options: {
            selectId: "",
           changeSelection: "",
           disableSomeParts: ""
        },

        _create: function () {
            customChangeSelection=this.options.changeSelection;
            customDisableSomeParts=this.options.disableSomeParts;
            this.wrapper = $("<span>")
                .addClass("custom-combobox")
                .insertAfter(this.element);

            this.element.hide();
            this._createAutocomplete();
            this._createShowAllButton();
        },

        _createAutocomplete: function () {
            var selected = this.element.children(":selected"),
                value = selected.val() ? selected.text() : $(this.options.selectId + " option:selected").text();

            this.input = $("<input>")
                .appendTo(this.wrapper)
                .val(value)
                .attr("id", (this.options.selectId).substring(1))
                .attr("title", "")
                .addClass("custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left")
                .click(function() {
                    $(".custom-combobox-toggle").trigger("click");
                })
                .on("keyup", function () {
                    if (!$(this).val()) {
                        if(customDisableSomeParts != ""){
                            customDisableSomeParts();
                        }
                    }
                })
                .width(Math.min((this.element.width() + 35), 590))
                .autocomplete({
                 delay: 0,
                 minLength: 0,
                 source: $.proxy(this, "_source")
                });

            this._on(this.input, {
                autocompleteselect: function (event, ui) {
                    ui.item.option.selected = true;
                    this._trigger("select", event, {
                        item: ui.item.option
                    });
                    //changeParty();
                    console.log(customChangeSelection);
                    customChangeSelection();
                },

                autocompletechange: "_removeIfInvalid"
            });
        },

        _createShowAllButton: function () {
            var input = this.input,
                wasOpen = false;

            $("<a>")
                .attr("tabIndex", -1)
                .appendTo(this.wrapper)
                .button({
                    icons: {
                        primary: "ui-icon-triangle-1-s"
                    },
                    text: false
                })
                .removeClass("ui-corner-all")
                .addClass("custom-combobox-toggle ui-corner-right")
                .mousedown(function () {
                    wasOpen = input.autocomplete("widget").is(":visible");
                })
                .click(function () {
                    input.focus();

                    // Close if already visible
                    if (wasOpen) {
                        return;
                    }

                    // Pass empty string as value to search for, displaying all results
                    input.autocomplete("search", "");
                });
        },

        _source: function (request, response) {
            var matcher = new RegExp($.ui.autocomplete.escapeRegex(request.term), "i");
            response(this.element.children("option").map(function () {
                var text = $(this).text();
                if (this.value && ( !request.term || matcher.test(text) ))
                    return {
                        label: text,
                        value: text,
                        option: this
                    };
            }));
        },

        _removeIfInvalid: function (event, ui) {

            // Selected an item, nothing to do
            if (ui.item) {
                return;
            }

            // Search for a match (case-insensitive)
            var value = this.input.val(),
                valueLowerCase = value.toLowerCase(),
                valid = false;
            this.element.children("option").each(function () {
                if ($(this).text().toLowerCase() === valueLowerCase) {
                    this.selected = valid = true;
                    return false;
                }
            });

            // Found a match, nothing to do
            if (valid && value) {
                return;
            }

            // Remove invalid value
            this.input.val("");
            this.element.val("");
            this.input.autocomplete("instance").term = "";
            //disablePartyData();
            if(customDisableSomeParts != ""){
               customDisableSomeParts();
            }

        },

        _destroy: function () {
            this.wrapper.remove();
            this.element.show();
        }
    });
})(jQuery);