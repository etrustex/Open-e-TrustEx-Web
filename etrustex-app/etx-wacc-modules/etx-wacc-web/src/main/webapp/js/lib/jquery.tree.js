(function ($, undefined) {
    $.widget("smart.tree", {


        _attachLi: function (li, parent, position) {

            var ul = parent.find('ul:first');

            if (ul.length) {
                if ((undefined == position) || (ul.children('li').length < position)) {
                    ul.append(li);
                } else {
                    if (position == 0) {
                        position = position + 1;
                    }
                    ul.children('li:nth-child(' + position + ')').before(li);
                }
            } else {
                ul = $('<ul/>');
                parent.append(ul.append(li));
            }

        },


        _attachNode: function (li, parentLi, position, parentExpand) {

            if (parentExpand == undefined) {
                parentExpand = true;
            }

            if (undefined == parentLi) {

                var parent = this.element;

                this._attachLi(li, parent, position);

                //initialize nodes from core to call all components initialize methods
                this._initNode(li);

            } else {

                var parent = parentLi;

                this._attachLi(li, parent, position);

                if (parentExpand) {
                    parent.removeClass('leaf collapsed').addClass('expanded');
                } else {
                    parent.removeClass('leaf').addClass('collapsed');
                }

                //initialize nodes from core to call all components initialize methods
                this._initNode(li);
                this._initNode(parent);
            }
        },


        _buildNode: function (attributes) {

            attributes = $.extend(true, this.options.defaultNodeAttributes, attributes);

            // create new node label
            var span = $('<span/>', attributes.span);

            // create node
            var li = $('<li/>', attributes.li);

            // if checkbox component is active, new node must contain checkbox input
            if ($.inArray('checkbox', this.options.components) > -1) {
                var input = $('<input/>',
                    attributes.input
                );
                li.append(input);
            }

            li.append(span);

            return li;
        },


        _create: function () {

            var t = this;

            // add jQueryUI css widget classes
            this.element.addClass('ui-widget ui-widget-content smart-tree');

            // initialize requested features
            if (this.options.checkbox) {
                this._initCheckboxTree();
            }
            if (this.options.collapsible) {
                this._initCollapsibleTree();
            }
            if (this.options.dnd) {
                this._initDndTree();
            }
            if (this.options.selectable) {
                this._initSelectableTree();
            }
            if (this.options.lazyLoading) {
                this._initLazyTree();
            }

            this.element.find('li').each(function () {
                t._initNode($(this));
            });

            // initialize object passed nodes
            if ((this.options.nodes != null) && (this.options.nodes instanceof Object)) {
                t._initFromObject(this.options.nodes);
            }

            // initialize ajax nodes
            if ((this.options.nodesInitUrl != null)) {
                t._initFromAjax(this.options.nodesInitUrl);
            }
        },


        _destroy: function () {
            $.Widget.prototype.destroy.call(this);
        },

        _detachNode: function (li) {

            var parentLi = this.parentNode(li);

            var ul = parentLi.find('ul:first');

            if (ul.children().length == 1) {
                ul.detach();
                parentLi.removeClass('collapsed expanded').addClass('leaf')
            } else {
                li.detach();
            }

            this._initNode(parentLi);
        },


        _initNode: function (li) {
            li.children('span:last').addClass('smart-tree-label');

            if (this.options.checkbox) {
                this._initCheckboxNode(li);
            }
            if (this.options.collapsible) {
                this._initCollapsibleNode(li);
            }
            if (this.options.dnd) {
                this._initDndNode(li);
            }
            if (this.options.selectable) {
                this._initSelectableNode(li);
            }
            if (this.options.lazyLoading) {
                this._initLazyNode(li);
            }
        },


        _initFromObject: function (nodesObject) {
            var t = this;

            $.each(nodesObject, function (key, value) {
                t.addNode(value);
            });
        },

        addNode: function (attributes, parentLi, position, parentExpand) {

            var t = this;

            var li = this._buildNode(attributes);

            if ((undefined == parentLi) || 0 == parentLi.length) {
                this._attachNode($(li), undefined, position, false);
            } else {
                this._attachNode($(li), $(parentLi), position, parentExpand);
            }

            if (undefined != attributes.children) {
                $.each(attributes.children, function (key, value) {
                    t.addNode(value, li);
                });
            }

            t._trigger('add', true, li);
        },


        isRoot: function (li) {

            li = $(li);

            var parents = li.parentsUntil('.smart-tree');

            return 1 == parents.length;
        },


        moveNode: function (li, parentLi, position) {

            this._detachNode($(li));

            if ((undefined == parentLi) || 0 == parentLi.length) {
                this._attachNode($(li), undefined, position);
            } else {
                this._attachNode($(li), $(parentLi), position);
            }

            this._trigger('move', true, $(li));
        },

        parentNode: function (li) {
            return $(li).parents('li:first');
        },


        removeNode: function (li) {

            this._detachNode($(li));

            this._trigger('remove', true, $(li));

        },

        _allDescendantChecked: function (li) {
            return (li.find('li input:checkbox:not(:checked)').length == 0);
        },


        _checkAncestors: function (li) {
            li.parentsUntil('smart-tree').filter('li').find('input:checkbox:first:not(:checked)').prop('checked', true).change();
        },


        _checkDescendants: function (li) {
            li.find('li input:checkbox:not(:checked)').prop('checked', true).change();
        },


        _checkOthers: function (li) {
            var t = this;
            li.addClass('exclude');
            li.parents('li').addClass('exclude');
            li.find('li').addClass('exclude');
            $(this.element).find('li').each(function () {
                if (!$(this).hasClass('exclude')) {
                    $(this).find('input:checkbox:first:not(:checked)').prop('checked', true).change();
                }
            });
            $(this.element).find('li').removeClass('exclude');
        },

        _initCheckboxTree: function () {

            var t = this;

            // bind node uncheck event
            this.element.on('click', 'input:checkbox:not(:checked)', function () {
                t.uncheck(t.parentNode($(this)));
            });

            // bind node check event
            this.element.on('click', 'input:checkbox:checked', function () {
                t.check(t.parentNode($(this)));
            });

            // bind collapse on uncheck event
            if (this.options.onUncheck.node == 'collapse') {
                this.element.on("click", 'input:checkbox:not(:checked)', function () {
                    t.collapse(t.parentNode($(this)));
                });
            } else

            // bind expand on uncheck event
            if (this.options.onUncheck.node == 'expand') {
                this.element.on("click", 'input:checkbox:not(:checked)', function () {
                    t.expand(t.parentNode($(this)));
                });
            }

            // bind collapse on check event
            if (this.options.onCheck.node == 'collapse') {
                this.element.on("click", 'input:checkbox:checked', function () {
                    t.collapse(t.parentNode($(this)));
                });
            } else

            // bind expand on check event
            if (this.options.onCheck.node == 'expand') {
                this.element.on("click", 'input:checkbox:checked', function () {
                    t.expand(t.parentNode($(this)));
                });
            }
        },


        _initCheckboxNode: function (li) {

        },


        _uncheckAncestors: function (li) {
            li.parentsUntil('smart-tree').filter('li').find('input:checkbox:first:checked').prop('checked', false).change();
        },


        _uncheckDescendants: function (li) {
            li.find('li input:checkbox:checked').prop('checked', false).change();
        },


        _uncheckOthers: function (li) {
            var t = this;
            li.addClass('exclude');
            li.parents('li').addClass('exclude');
            li.find('li').addClass('exclude');
            $(this.element).find('li').each(function () {
                if (!$(this).hasClass('exclude')) {
                    $(this).find('input:checkbox:first:checked').prop('checked', false).change();
                }
            });
            $(this.element).find('li').removeClass('exclude');
        },


        check: function (li) {

            li = $(li);

            li.find('input:checkbox:first:not(:checked)').prop('checked', true).change();

            // handle others
            if (this.options.onCheck.others == 'check') {
                this._checkOthers(li);
            } else if (this.options.onCheck.others == 'uncheck') {
                this._uncheckOthers(li);
            }

            // handle descendants
            if (this.options.onCheck.descendants == 'check') {
                this._checkDescendants(li);
            } else if (this.options.onCheck.descendants == 'uncheck') {
                this._uncheckDescendants(li);
            }

            // handle ancestors
            if (this.options.onCheck.ancestors == 'check') {
                this._checkAncestors(li);
            } else if (this.options.onCheck.ancestors == 'uncheck') {
                this._uncheckAncestors(li);
            } else if (this.options.onCheck.ancestors == 'checkIfFull') {
                var isRoot = this.isRoot(li);
                var allDescendantChecked = this._allDescendantChecked(this.parentNode(li));
                if (!isRoot && allDescendantChecked) {
                    this.check(this.parentNode(li));
                }
            }
        },


        checkAll: function () {
            $(this.element).find('input:checkbox:not(:checked)').prop('checked', true).change();
        },


        uncheck: function (li) {

            li = $(li);

            li.find('input:checkbox:first:checked').prop('checked', false).change();

            // handle others
            if (this.options.onUncheck.others == 'check') {
                this._checkOthers(li);
            } else if (this.options.onUncheck.others == 'uncheck') {
                this._uncheckOthers(li);
            }

            // handle descendants
            if (this.options.onUncheck.descendants == 'check') {
                this._checkDescendants(li);
            } else if (this.options.onUncheck.descendants == 'uncheck') {
                this._uncheckDescendants(li);
            }

            // handle ancestors
            if (this.options.onUncheck.ancestors == 'check') {
                this._checkAncestors(li);
            } else if (this.options.onUncheck.ancestors == 'uncheck') {
                this._uncheckAncestors(li);
            }

        },


        uncheckAll: function () {
            $(this.element).find('input:checkbox:checked').prop('checked', false).change();
        },

        _initCollapsibleTree: function () {

            var t = this

            // bind collapse/expand event
            this.element.on("click", 'li span.smart-tree-anchor', function () {
                var li = t.parentNode($(this));

                if (li.hasClass('collapsed')) {
                    t.expand(li);
                } else if (li.hasClass('expanded')) {
                    t.collapse(li);
                }
            });
        },

        _initCollapsibleNode: function (li) {

            var t = this;

            // add anchor if needed
            var anchor = li.children('span.smart-tree-anchor');
            if (anchor.length < 1) {
                li.prepend($('<span />', {
                    'class': 'smart-tree-anchor'
                }));
            }

            // initialize nodes
            // if is specified a state li element, consolidate it
            if (li.hasClass("leaf")) {
                t._markAsLeaf(li);
            } else if (li.hasClass("collapsed")) {
                t.collapse(li, false, true);
            } else if (li.hasClass("expanded")) {
                t.expand(li, false, true);
            } else

            // otherwise, guess state from tree structure
            if (li.is("li:not(:has(ul))")) {
                t._markAsLeaf(li);
            } else {
                t._markAsExpanded(li);
            }
        },


        _markAsCollapsed: function (li) {

            var anchor = li.children('span.smart-tree-anchor');

            anchor.removeClass('ui-icon ' + this.options.expandUiIcon + ' ' + this.options.leafUiIcon);

            if (this.options.collapseUiIcon.length > 0) {
                anchor.addClass('ui-icon ' + this.options.collapseUiIcon)
            }

            li.removeClass("leaf").removeClass("expanded").addClass("collapsed");
        },


        _markAsExpanded: function (li) {

            var anchor = li.children('span.smart-tree-anchor');

            anchor.removeClass('ui-icon ' + this.options.collapseUiIcon + ' ' + this.options.leafUiIcon);

            if (this.options.expandUiIcon.length > 0) {
                anchor.addClass('ui-icon ' + this.options.expandUiIcon)
            }

            li.removeClass("leaf").removeClass("collapsed").addClass("expanded");
        },


        _markAsLeaf: function (li) {

            var anchor = li.children('span.smart-tree-anchor');

            anchor.removeClass('ui-icon ' + this.options.collapseUiIcon + ' ' + this.options.expandUiIcon);

            if (this.options.leafUiIcon.length > 0) {
                anchor.addClass('ui-icon ' + this.options.leafUiIcon)
            }

            li.removeClass("collapsed").removeClass("expanded").addClass("leaf");
        },


        _unmark: function () {
            li.removeClass("collapsed expanded leaf");
        },


        collapse: function (li, effect, force) {

            li = $(li);

            if (force == undefined) {
                force = false;
            }

            if (!force && (li.hasClass('collapsed') || li.hasClass('leaf'))) {
                return;
            }

            if (effect == undefined) {
                effect = true;
            }

            var t = this;

            if (effect) {
                li.children("ul").hide(this.options.collapseEffect, {}, this.options.collapseDuration);

                setTimeout(function () {
                    t._markAsCollapsed(li, t.options);
                }, t.options.collapseDuration);
            } else {
                li.children("ul").hide();
                t._markAsCollapsed(li, t.options);
            }
            t._trigger('collapse', true, li);
        },


        collapseAll: function () {
            var t = this;
            $(this.element).find('li.expanded').each(function () {
                t.collapse($(this));
            });
        },


        expand: function (li, effect, force) {

            li = $(li);

            if (force == undefined) {
                force = false;
            }

            if (!force && (li.hasClass('expanded') || li.hasClass('leaf'))) {
                return;
            }

            if (effect == undefined) {
                effect = true;
            }

            var t = this;

            t._trigger('preexpand', true, li);

            if (effect) {
                li.children("ul").show(t.options.expandEffect, {}, t.options.expandDuration);

                setTimeout(function () {
                    t._markAsExpanded(li, t.options);
                }, t.options.expandDuration);
            } else {
                li.children("ul").show();
                t._markAsExpanded(li, t.options);
            }

            t._trigger('expand', true, li);
        },


        expandAll: function () {
            var t = this;
            $(this.element).find('li.collapsed').each(function () {
                t.expand($(this));
            });
        },


        _initDndTree: function () {

        },


        _initDndNode: function (li) {

            var t = this;

            var span = $('<span/>', {
                'class': 'prepended',
                html: '<br/>'
            }).droppable({
                /*  hoverClass: 'over',
                 drop: function (event, ui) {

                 var li = $(this).closest('li');

                 // if node will be a root parent is undefined, else catch new parentLi
                 if (t.isRoot(li)) {
                 var parentLi = undefined;
                 var droppable = t.element;
                 } else {
                 var parentLi = li.parent().closest('li');
                 var droppable = parentLi;

                 // prevent loops
                 if ($(ui.draggable.parent('li')).find(parentLi).length) {
                 return;
                 }
                 }

                 var position = $($(this).parent('li')).index() + 1;

                 t.moveNode(ui.draggable.parent('li'), parentLi, position);
                 t._trigger('drop', event, {draggable: ui.draggable, droppable: parentLi});
                 }*/
            });

            $(li).find('.smart-tree-label:first').after(span);

            /*  $(li).find('.smart-tree-label:first').draggable({
             start: function (event, ui) {
             $(this).parent('li').find('ul, .prepended').css('visibility', 'hidden');
             $(this).parent('li').find('.droppable-label').css('display', 'none');
             },
             stop: function (event, ui) {
             $(this).parent('li').find('ul').css('visibility', 'visible');
             $(this).parent('li').find('.prepended').css('visibility', '');
             $(this).parent('li').find('.droppable-label').css('display', 'inherit');
             },
             revert: true,
             revertDuration: 0
             });*/

            var span = $('<span/>', {
                'class': 'droppable-label',
                html: '<br/>'
            }).droppable({
                /*  drop: function (event, ui) {
                 var li = $(this).closest('li');

                 // prevent loops
                 if ($(ui.draggable.parent('li')).find(li).length) {
                 return;
                 }

                 t.moveNode(ui.draggable.parent('li'), li, 1);
                 t._trigger('drop', event, {draggable: ui.draggable, droppable: li});
                 },
                 over: function (event, ui) {
                 $(this).parent('li').find('.smart-tree-label:first').addClass('ui-state-hover');
                 },
                 out: function (event, ui) {
                 $(this).parent('li').find('.smart-tree-label:first').removeClass('ui-state-hover');
                 }*/
            });

            $(li).find('.smart-tree-label:first').after(span);

        },

        _initSelectableTree: function () {
            var t = this;

            this.element.on("click", '.smart-tree-label', function () {
                var li = $(this);
                if (li.hasClass(t.options.selectUiClass)) {
                    t.deselect($(this).parent(li));
                } else {
                    t.select($(this).parent('li'));
                }
            });
        },


        _deselect: function (li) {
            li.find('span.smart-tree-label:first').removeClass(this.options.selectUiClass);
            this._trigger('deselect', true, li);
        },


        _deselectAll: function () {
            var t = this;
            this.element.find('.smart-tree-label.' + this.options.selectUiClass).each(function () {
                t._deselect($(this).parent('li'));
            });
        },


        _destroySelectable: function () {
        },


        _initSelectableNode: function (li) {

        },


        _select: function (li) {
            li.find('span.smart-tree-label:first').addClass(this.options.selectUiClass);
            this._trigger('select', true, li);
        },


        deselect: function (li) {
            li = $(li);
            this._deselect(li);
        },


        select: function (li) {
            li = $(li);

            if (this.options.selectUnique) {
                this._deselectAll();
            }

            this._select(li);
        },


        selected: function () {
            var selected = this.element.find('.smart-tree-label.' + this.options.selectUiClass);
            return $(selected).parent();
        },

        _initLazyTree: function () {
            var t = this;

            // bind lazy loading on expand event
            if (this.options.lazyLoading) {

                t.element.on("treepreexpand", function (event, element) {
                    var li = $(this);
                    if ($(element).find('ul').length) {
                        return;
                    }
                    t._lazyAdd(t.options.nodesLazyUrl, $(element));
                });
            }

        },


        _initLazyNode: function (li) {

        },

        _initFromAjax: function (urlString) {
            var t = this;

            $.ajax({
                url: urlString,
                dataType: 'json',
                beforeSend: function () {

                },
                complete: function () {

                },
                data: {},
                success: function (data) {
                    $.each(data.nodes, function (key, value) {
                        t.addNode(value);
                    });
                }
            });
        },

        _lazyAdd: function (urlString, parentLi) {
            var t = this;

            $.ajax({
                async: false,
                url: urlString,
                dataType: 'json',
                data: {
                    node: parentLi.attr('id')
                },
                success: function (data) {

                    $.each(data.nodes, function (key, value) {
                        t.addNode(value, parentLi, undefined, false);
                    });
                }
            });
        },


        options: {


            defaultNodeAttributes: {
                span: {
                    html: 'new node'
                },
                li: {
                    'class': 'leaf' //@todo handle leaf class
                },
                input: {
                    type: 'checkbox'
                }
            },


            nodes: null,

            checkbox: true,


            onCheck: {


                ancestors: 'check',


                descendants: 'check',


                node: '',

                others: ''
            },


            onUncheck: {


                ancestors: '',


                descendants: 'uncheck',


                node: '',


                others: ''
            },


            collapsible: true,


            collapseDuration: 500,


            collapseEffect: 'blind',

            collapseUiIcon: 'ui-icon-triangle-1-e',

            expandDuration: 500,

            expandEffect: 'blind',

            expandUiIcon: 'ui-icon-triangle-1-se',

            leafUiIcon: '',

            dnd: true,

            drop: function (event, element) {
            },

            selectable: false,


            deselect: function (event, element) {
            },


            selectUnique: true,


            select: function (event, element) {
            },


            lazyLoading: false,


            dataEditUrl: '',


            nodesInitUrl: '',


            nodesLazyUrl: ''
        }
    });

})(jQuery);
